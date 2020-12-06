package com.boot.business.syslog.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.business.syslog.mapper.LogVideoMapper;
import com.boot.business.syslog.model.param.LogVideoSaveParam;
import com.boot.business.syslog.model.po.LogVideo;
import org.springframework.stereotype.Service;

/**
 * LogSysUserService
 *
 * @author XINAN
 */
@Service
public class LogVideoService extends ServiceImpl<LogVideoMapper, LogVideo> implements LogVideoFacade {

    @Override
    public boolean saveLog(LogVideoSaveParam param) {
        param.setLogTime(System.currentTimeMillis());
        return new LogVideo().warpT(param).insert();
    }
}
