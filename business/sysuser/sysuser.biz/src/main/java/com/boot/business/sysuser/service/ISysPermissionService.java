package com.boot.business.sysuser.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.business.sysuser.model.dto.PermissionDTO;
import com.boot.business.sysuser.model.po.SysPermission;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author polarbear
 * @since 2019-10-30
 */
public interface ISysPermissionService extends IService<SysPermission> {

    List<PermissionDTO> getByParentId(Long parentId);

    PermissionDTO warpDTO(SysPermission permission);

    /**
     * 获取资源码
     *
     * @param userId
     * @return
     */
    Set<String> getMenuResource(Long userId);

    List<PermissionDTO> getPermissionList(Long userId);
}
