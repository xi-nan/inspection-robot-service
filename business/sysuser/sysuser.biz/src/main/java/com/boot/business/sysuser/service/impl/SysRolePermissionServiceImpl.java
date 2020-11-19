package com.boot.business.sysuser.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.business.sysuser.mapper.SysRolePermissionMapper;
import com.boot.business.sysuser.model.dto.PermissionDTO;
import com.boot.business.sysuser.model.po.SysPermission;
import com.boot.business.sysuser.model.po.SysRolePermission;
import com.boot.business.sysuser.model.po.SysUserRole;
import com.boot.business.sysuser.service.ISysPermissionService;
import com.boot.business.sysuser.service.ISysRolePermissionService;
import com.boot.business.sysuser.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements ISysRolePermissionService {

    @Autowired
    private ISysUserRoleService userRoleService;

    @Autowired
    private ISysPermissionService permissionService;

    @Override
    public List<String> findCodesByRoleId(Long roleId) {
        List<SysRolePermission> list = this.list(Wrappers.<SysRolePermission>lambdaQuery().eq(SysRolePermission::getRoleId, roleId));
        if (CollUtil.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list.stream().map(rp -> {
            SysPermission byId = permissionService.getById(rp.getPermissionId());
            return null == byId ? "" : byId.getCode();
        })
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());
    }

    @Override
    public List<PermissionDTO> findByRoleId(Long roleId) {
        List<SysRolePermission> list = this.list(Wrappers.<SysRolePermission>lambdaQuery().eq(SysRolePermission::getRoleId, roleId));
        if (CollUtil.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list.stream().map(rp -> {
            SysPermission byId = permissionService.getById(rp.getPermissionId());
            return null == byId ? null : byId.warpR(PermissionDTO.class);
        })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<PermissionDTO> findByUserId(Long userId) {
        List<PermissionDTO> list = new ArrayList<>();
        List<SysUserRole> userRoleList = userRoleService.findByUserId(userId);
        if (CollUtil.isEmpty(userRoleList)) {
            return list;
        }
        for (SysUserRole userRole : userRoleList) {
            List<SysRolePermission> rolePermissions = this.list(Wrappers.<SysRolePermission>lambdaQuery().eq(SysRolePermission::getRoleId, userRole.getRoleId()));
            if (CollUtil.isEmpty(rolePermissions)) {
                continue;
            }
            for (SysRolePermission rp : rolePermissions) {
                SysPermission permission = permissionService.getById(rp.getPermissionId());
                if (null != permission) {
                    list.add(permission.warpR(PermissionDTO.class));
                }
            }
        }
        return list;
    }

}
