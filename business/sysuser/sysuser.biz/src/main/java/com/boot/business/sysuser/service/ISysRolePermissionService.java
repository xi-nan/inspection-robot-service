package com.boot.business.sysuser.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.business.sysuser.model.dto.PermissionDTO;
import com.boot.business.sysuser.model.po.SysRolePermission;

import java.util.List;


public interface ISysRolePermissionService extends IService<SysRolePermission> {

    List<String> findCodesByRoleId(Long roleId);

    List<PermissionDTO> findByRoleId(Long roleId);

    List<PermissionDTO> findByUserId(Long userId);
}
