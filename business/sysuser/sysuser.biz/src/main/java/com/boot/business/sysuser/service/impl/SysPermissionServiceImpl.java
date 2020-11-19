package com.boot.business.sysuser.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.business.sysuser.mapper.SysPermissionMapper;
import com.boot.business.sysuser.model.dto.PermissionDTO;
import com.boot.business.sysuser.model.po.SysPermission;
import com.boot.business.sysuser.service.ISysPermissionService;
import com.boot.business.sysuser.service.ISysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("sysPermissionService")
//@CacheConfig(cacheNames = "SysUserPermission")
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {


    @Autowired
    private ISysRolePermissionService rolePermissionService;

    @Override
    public List<PermissionDTO> getByParentId(Long parentId) {
        return this.list(Wrappers.<SysPermission>lambdaQuery().eq(SysPermission::getParentId, parentId).orderByAsc(SysPermission::getSortOrder))
                .stream().map(this::warpDTO).collect(Collectors.toList());
    }

    @Override
    public PermissionDTO warpDTO(SysPermission permission) {
        PermissionDTO dto = permission.warpR(PermissionDTO.class);
        dto.setChildren(this.getByParentId(dto.getId()));
        return dto;
    }

    @Override
    public Set<String> getMenuResource(Long userId) {
        if (null == userId || userId.equals(1L)) {
            return this.list().stream().map(SysPermission::getCode).collect(Collectors.toSet());
        }
        return rolePermissionService.findByUserId(userId)
                .stream().map(PermissionDTO::getCode).collect(Collectors.toSet());

    }

    @Override
    public List<PermissionDTO> getPermissionList(Long userId) {
        if (null == userId || userId.equals(1L)) {
            return this.getByParentId(0L);
        } else {
            return PermissionDTO.makeTree(rolePermissionService.findByUserId(userId));
        }
    }


}
