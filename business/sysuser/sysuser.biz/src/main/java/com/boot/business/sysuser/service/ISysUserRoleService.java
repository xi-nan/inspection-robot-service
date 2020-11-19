package com.boot.business.sysuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.business.sysuser.model.po.SysUserRole;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author polarbear
 * @since 2019-10-30
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    List<SysUserRole> findByUserId(Long userId);

    List<SysUserRole> findByRoleId(Long roleId);

    Boolean bindUserRole(Long userId, Long roleId);

}
