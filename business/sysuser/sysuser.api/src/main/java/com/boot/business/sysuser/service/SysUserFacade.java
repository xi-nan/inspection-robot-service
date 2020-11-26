package com.boot.business.sysuser.service;

import com.boot.business.sysuser.model.dto.SysUserDTO;

/**
 * SysUserFacade
 *
 * @author XINAN
 * @date 2020/6/16
 */
public interface SysUserFacade {

    SysUserDTO getDtoById(Long id);
}
