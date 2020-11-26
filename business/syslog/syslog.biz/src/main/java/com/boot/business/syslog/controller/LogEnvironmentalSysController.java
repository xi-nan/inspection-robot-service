package com.boot.business.syslog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.boot.business.syslog.model.dto.LogEnvironmentalDTO;
import com.boot.business.syslog.model.param.LogPageParam;
import com.boot.business.syslog.model.po.LogEnvironmental;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "后台-环境日志")
@RestController
@RequestMapping(value = "/sys/log/environmental")
public class LogEnvironmentalSysController {

    @GetMapping("/details/{id}")
    @ApiOperation("查看详情")
    public LogEnvironmentalDTO getById(@PathVariable Long id) {
        return LogEnvironmentalDTO.warp(new LogEnvironmental().selectById(id));
    }

    @ApiOperation("获取日志列表(分页)")
    @PostMapping("/page")
    public IPage<LogEnvironmentalDTO> page(@RequestBody LogPageParam param) {
        return new LogEnvironmental().selectPage(param.page(), Wrappers.<LogEnvironmental>lambdaQuery()
                .orderByDesc(LogEnvironmental::getLogTime)
                .ge(param.getLogTimeEnd() != null, LogEnvironmental::getLogTime, param.getLogTimeStart())
                .le(param.getLogTimeEnd() != null, LogEnvironmental::getLogTime, param.getLogTimeEnd()))
                .convert(LogEnvironmentalDTO::warp);
    }

}
