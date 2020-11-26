package com.boot.business.appuser.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.business.appuser.model.dto.AppUserDTO;
import com.boot.business.appuser.model.param.AppUserLoginParam;
import com.boot.business.appuser.model.param.AppUserPageParam;
import com.boot.business.appuser.model.param.AppUserSaveParam;
import com.boot.business.appuser.model.po.AppUser;
import com.boot.commons.core.security.JwtUser;

import java.time.Duration;

/**
 * IAppUserService
 *
 * @author XINAN
 * @date 2019/7/19
 */
public interface IAppUserService extends IService<AppUser>, AppUserFacade {

    JwtUser login(AppUserLoginParam param, Duration expiration, Boolean autoRenewal);

    AppUser findByName(String name);

//    SysUserDTO getDtoById(Long id);

    AppUser findByIdNotNull(Long id);

    Boolean checkPwd(Long id, String oldPassword);

    Boolean updPwd(Long id, String oldPassword, String newPassword);

    Boolean resetPwd(Long id);

    Boolean save(AppUserSaveParam param);

    IPage<AppUserDTO> findDtoPage(AppUserPageParam param);
}
