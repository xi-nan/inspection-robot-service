package com.boot.business.sysuser.service;

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

}
