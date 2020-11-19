package com.boot.business.sysuser.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.business.sysuser.model.dto.SysUserDTO;
import com.boot.business.sysuser.model.param.SysUserLoginParam;
import com.boot.business.sysuser.model.param.SysUserPageParam;
import com.boot.business.sysuser.model.param.SysUserSaveParam;
import com.boot.business.sysuser.model.po.SysUser;
import com.boot.commons.core.security.JwtUser;

import java.time.Duration;

/**
 * IAdminService
 *
 * @author XINAN
 * @date 2019/7/19
 */
public interface ISysUserService extends IService<SysUser> {

    JwtUser login(SysUserLoginParam param, Duration expiration, Boolean autoRenewal);

    SysUser findByName(String name);

    SysUserDTO getDtoById(Long id);

    SysUser findByIdNotNull(Long id);

    Boolean checkPwd(Long id, String oldPassword);

    Boolean updPwd(Long id, String oldPassword, String newPassword);

    Boolean resetPwd(Long id);

    Boolean save(SysUserSaveParam param);

    IPage<SysUserDTO> findDtoPage(SysUserPageParam param);

}
