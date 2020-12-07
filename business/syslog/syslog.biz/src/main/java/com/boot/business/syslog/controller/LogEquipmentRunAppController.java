package com.boot.business.syslog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.boot.business.syslog.model.dto.LogEquipmentRunDTO;
import com.boot.business.syslog.model.param.LogEquipmentRunSaveParam;
import com.boot.business.syslog.model.param.LogPageParam;
import com.boot.business.syslog.model.po.LogEquipmentRun;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "客户端-设备运行日志")
@RestController
@RequestMapping(value = "/app/log/equipmentRun")
public class LogEquipmentRunAppController {

    @ApiOperation("保存日志记录")
    @PostMapping("/save")
    public void add(@RequestBody LogEquipmentRunSaveParam param) {
        new LogEquipmentRun().warpT(param).insert();
    }

    @GetMapping("/details/{id}")
    @ApiOperation("查看详情")
    public LogEquipmentRunDTO getById(@PathVariable Long id) {
        return LogEquipmentRunDTO.warp(new LogEquipmentRun().selectById(id));
    }

    @ApiOperation("获取日志列表(分页)")
    @PostMapping("/page")
    public IPage<LogEquipmentRunDTO> page(@RequestBody LogPageParam param) {
        return new LogEquipmentRun().selectPage(param.page(), Wrappers.<LogEquipmentRun>lambdaQuery()
                .orderByDesc(LogEquipmentRun::getLogTime)
                .ge(param.getEquipmentId() != null, LogEquipmentRun::getEquipmentId, param.getEquipmentId())
                .ge(param.getLogTimeEnd() != null, LogEquipmentRun::getLogTime, param.getLogTimeStart())
                .le(param.getLogTimeEnd() != null, LogEquipmentRun::getLogTime, param.getLogTimeEnd()))
                .convert(LogEquipmentRunDTO::warp);
    }

}
