package com.boot.business.sysuser.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.business.sysuser.mapper.SysRoleMapper;
import com.boot.business.sysuser.model.dto.PermissionDTO;
import com.boot.business.sysuser.model.dto.RoleDTO;
import com.boot.business.sysuser.model.param.RoleSaveParam;
import com.boot.business.sysuser.model.po.SysPermission;
import com.boot.business.sysuser.model.po.SysRole;
import com.boot.business.sysuser.model.po.SysRolePermission;
import com.boot.business.sysuser.model.po.SysUserRole;
import com.boot.business.sysuser.service.ISysPermissionService;
import com.boot.business.sysuser.service.ISysRolePermissionService;
import com.boot.business.sysuser.service.ISysRoleService;
import com.boot.business.sysuser.service.ISysUserRoleService;
import com.boot.commons.core.exception.enums.ErrCodeEnum;
import com.boot.commons.core.security.JwtTokenUtil;
import com.boot.commons.core.security.UserType;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private ISysPermissionService permissionService;

    @Autowired
    private ISysRolePermissionService rolePermissionService;

    @Autowired
    private ISysUserRoleService userRoleService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public List<RoleDTO> allRoles() {
        return super.list().stream().map(RoleDTO::warp).collect(Collectors.toList());
    }

    @Override
    public RoleDTO getDTOById(Long roleId) {
        return this.warpDTO(super.getById(roleId));
    }

    private RoleDTO warpDTO(SysRole role) {
        if (null == role) {
            return null;
        }
        RoleDTO dto = role.warpR(RoleDTO.class);
        Long[] permissionIds = rolePermissionService.findByRoleId(role.getId())
                .stream()
                .map(PermissionDTO::getId)
                .filter(perId -> permissionService.count(Wrappers.<SysPermission>lambdaQuery().eq(SysPermission::getParentId, perId)) <= 0)
                .toArray(Long[]::new);
        dto.setPermissionIds(permissionIds);
        return dto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean save(RoleSaveParam param) {
        SysRole sysRole = new SysRole().warpT(param);
        boolean flag = this.save(sysRole);
        if (flag) {
            ErrCodeEnum.E_10021.throwIf(!bindSoleAndPermission(sysRole.getId(), param.getPermissionList()), "角色添加失败!");
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean update(RoleSaveParam param) {
        // 修改角色属性
        ErrCodeEnum.E_10021.throwIf(!this.updateById(new SysRole().warpT(param)), "角色信息保存失败!");

        Long roleId = param.getId();
        List<Long> permissions = param.getPermissionList();
        // 已拥有的权限列表
        List<Long> hasList = rolePermissionService.list(Wrappers.<SysRolePermission>lambdaQuery()
                .eq(SysRolePermission::getRoleId, roleId)
                .in(SysRolePermission::getPermissionId, permissions))
                .stream().map(SysRolePermission::getPermissionId).collect(Collectors.toList());

        boolean hasFlag = !Collections.isEmpty(hasList);
        // 解绑已取消的权限
        rolePermissionService.remove(Wrappers.<SysRolePermission>lambdaQuery()
                .eq(SysRolePermission::getRoleId, roleId)
                .notIn(hasFlag, SysRolePermission::getPermissionId, hasList));
        if (hasFlag) {
            // 筛选未拥有的权限
            permissions.removeAll(hasList);
        }

        // 重新绑定角色和权限
        ErrCodeEnum.E_10021.throwIf(!bindSoleAndPermission(roleId, permissions), "绑定角色和权限失败!");

        // 清除角色对应的用户登录信息
        List<SysUserRole> byRoleId = userRoleService.findByRoleId(param.getId());
        for (SysUserRole sysUserRole : byRoleId) {
            jwtTokenUtil.cleanToken4UserId(UserType.ADMIN.name(), sysUserRole.getUserId());
        }
        return true;
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteRole(Long roleId) {
        ErrCodeEnum.E_20004.throwIf(null == roleId, "roleId不能为空!");
        // 删除角色关联权限
        rolePermissionService.remove(Wrappers.<SysRolePermission>lambdaQuery().eq(SysRolePermission::getRoleId, roleId));

        ErrCodeEnum.E_10022.throwIf(!this.removeById(roleId), "角色删除失败!");

        // 清除角色对应的用户登录信息
        List<SysUserRole> byRoleId = userRoleService.findByRoleId(roleId);
        for (SysUserRole sysUserRole : byRoleId) {
            jwtTokenUtil.cleanToken4UserId(UserType.ADMIN.name(), sysUserRole.getUserId());
        }
        return true;
    }

    /**
     * 绑定角色和权限
     *
     * @param roleId
     * @param permissionIds
     * @return
     */
    private Boolean bindSoleAndPermission(Long roleId, List<Long> permissionIds) {
        return rolePermissionService.saveBatch(permissionIds.stream().map(id -> new SysRolePermission(id, roleId)).collect(Collectors.toList()));
    }


}
