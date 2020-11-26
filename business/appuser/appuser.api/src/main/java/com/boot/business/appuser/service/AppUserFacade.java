package com.boot.business.appuser.service;

import com.boot.business.appuser.model.dto.AppUserDTO;

/**
 * AppUserFacade
 *
 * @author XINAN
 * @date 2020/6/17
 */
public interface AppUserFacade {

    AppUserDTO getDtoById(Long id);

    /**
     * 获取搜索关键词相关的用户ID列表
     *
     * @param searchKey
     * @return
     */
    Long[] searchUser(String searchKey);

}
