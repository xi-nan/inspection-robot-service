package com.boot.business.historicaldata.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.boot.business.historicaldata.model.dto.HistoryVideoDTO;
import com.boot.business.historicaldata.model.enums.VideoType;
import com.boot.business.historicaldata.model.param.HistoryVideoPageParam;
import com.boot.business.historicaldata.model.param.HistoryVideoSaveParam;
import com.boot.business.historicaldata.service.HistoryVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "客户端-历史视频")
@RestController
@RequestMapping(value = "/app/history/video")
public class HistoryVideoAppController {

    @Autowired
    private HistoryVideoService service;

    @ApiOperation("保存视频记录")
    @PostMapping("/{type}/save")
    public void add(@PathVariable VideoType type, @RequestBody HistoryVideoSaveParam param) {
        param.setVideoType(type);
        service.add(param);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除视频")
    public void getById(@PathVariable Long id) {
        service.del(id);
    }

    @ApiOperation("获取视频列表(分页)")
    @PostMapping("/{type}/page")
    public IPage<HistoryVideoDTO> page(@PathVariable VideoType type, @RequestBody HistoryVideoPageParam param) {
        return service.page(type, param);
    }

}
