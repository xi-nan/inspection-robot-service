package com.boot.business.syslog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.boot.business.syslog.model.dto.LogAlarmDTO;
import com.boot.business.syslog.model.param.LogPageParam;
import com.boot.business.syslog.model.po.LogAlarm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "后台-报警日志")
@RestController
@RequestMapping(value = "/sys/log/alarm")
public class LogAlarmSysController {

    @GetMapping("/details/{id}")
    @ApiOperation("查看详情")
    public LogAlarmDTO getById(@PathVariable Long id) {
        return LogAlarmDTO.warp(new LogAlarm().selectById(id));
    }

    @ApiOperation("获取日志列表(分页)")
    @PostMapping("/page")
    public IPage<LogAlarmDTO> page(@RequestBody LogPageParam param) {
        return new LogAlarm().selectPage(param.page(), Wrappers.<LogAlarm>lambdaQuery()
                .orderByDesc(LogAlarm::getLogTime)
                .ge(param.getEquipmentId() != null, LogAlarm::getEquipmentId, param.getEquipmentId())
                .ge(param.getLogTimeEnd() != null, LogAlarm::getLogTime, param.getLogTimeStart())
                .le(param.getLogTimeEnd() != null, LogAlarm::getLogTime, param.getLogTimeEnd()))
                .convert(LogAlarmDTO::warp);
    }

}
