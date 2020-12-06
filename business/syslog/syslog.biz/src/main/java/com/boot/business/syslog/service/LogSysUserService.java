package com.boot.business.syslog.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.business.syslog.mapper.LogSysUserMapper;
import com.boot.business.syslog.model.param.LogSysUserSaveParam;
import com.boot.business.syslog.model.po.LogSysUser;
import org.springframework.stereotype.Service;

/**
 * LogSysUserService
 *
 * @author XINAN
 */
@Service
public class LogSysUserService extends ServiceImpl<LogSysUserMapper, LogSysUser> implements LogSysUserFacade {


    @Override
    public boolean saveLog(LogSysUserSaveParam param) {
        param.setLogTime(System.currentTimeMillis());
        return new LogSysUser().warpT(param).insert();
    }
}
