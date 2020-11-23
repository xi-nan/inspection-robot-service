package com.boot.business.syslog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.boot.business.syslog.model.dto.LogVideoDTO;
import com.boot.business.syslog.model.param.LogPageParam;
import com.boot.business.syslog.model.po.LogVideo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "后台-视频日志")
@RestController
@RequestMapping(value = "/sys/log/video")
public class LogVideoSysController {

//    @ApiOperation("保存日志记录")
//    @PostMapping("/save")
//    public void add(@RequestBody LogVideoSaveParam param) {
//        new LogVideo().warpT(param).insert();
//    }

    @GetMapping("/details/{id}")
    @ApiOperation("查看详情")
    public LogVideoDTO getById(@PathVariable Long id) {
        return LogVideoDTO.warp(new LogVideo().selectById(id));
    }

    @ApiOperation("获取日志列表(分页)")
    @PostMapping("/page")
    public IPage<LogVideoDTO> page(@RequestBody LogPageParam param) {
        return new LogVideo().selectPage(param.page(), Wrappers.<LogVideo>lambdaQuery()
                .orderByDesc(LogVideo::getLogTime)
                .ge(param.getLogTimeEnd() != null, LogVideo::getLogTime, param.getLogTimeStart())
                .le(param.getLogTimeEnd() != null, LogVideo::getLogTime, param.getLogTimeEnd()))
                .convert(LogVideoDTO::warp);
    }

}
