package com.boot.business.syslog.service;


import com.boot.business.syslog.model.param.LogSysUserSaveParam;

public interface LogSysUserFacade {

    boolean saveLog(LogSysUserSaveParam param);

}
