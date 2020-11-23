package com.boot.business.sysuser.service;

import com.boot.business.sysuser.model.dto.SysUserDTO;

/**
 * SysUserFacade
 *
 * @author XINAN
 * @date 2020/6/16
 */
public interface SysUserFacade {

    /**
     * 获取搜索关键词相关的用户ID列表
     *
     * @param searchKey
     * @return
     */
    Long[] searchUser(String searchKey);

    SysUserDTO getDtoById(Long id);
}
