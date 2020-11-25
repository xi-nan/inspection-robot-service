package com.boot.business.syslog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.boot.business.syslog.model.dto.LogSysUserDTO;
import com.boot.business.syslog.model.param.LogPageParam;
import com.boot.business.syslog.model.po.LogSysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "后台-用户日志")
@RestController
@RequestMapping(value = "/sys/log/sysUser")
public class LogSysUserSysController {

    @GetMapping("/details/{id}")
    @ApiOperation("查看详情")
    public LogSysUserDTO getById(@PathVariable Long id) {
        return LogSysUserDTO.warp(new LogSysUser().selectById(id));
    }

    @ApiOperation("获取日志列表(分页)")
    @PostMapping("/page")
    public IPage<LogSysUserDTO> page(@RequestBody LogPageParam param) {
        return new LogSysUser().selectPage(param.page(), Wrappers.<LogSysUser>lambdaQuery()
                .orderByDesc(LogSysUser::getLogTime)
                .ge(param.getLogTimeEnd() != null, LogSysUser::getLogTime, param.getLogTimeStart())
                .le(param.getLogTimeEnd() != null, LogSysUser::getLogTime, param.getLogTimeEnd()))
                .convert(LogSysUserDTO::warp);
    }

}
