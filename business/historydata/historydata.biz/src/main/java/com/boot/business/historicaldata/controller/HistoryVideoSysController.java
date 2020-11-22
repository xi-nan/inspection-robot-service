package com.boot.business.historicaldata.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.boot.business.historicaldata.model.dto.HistoryVideoDTO;
import com.boot.business.historicaldata.model.enums.VideoType;
import com.boot.business.historicaldata.model.param.HistoryVideoPageParam;
import com.boot.business.historicaldata.model.param.HistoryVideoSaveParam;
import com.boot.business.historicaldata.model.po.HistoryVideo;
import com.boot.business.syslog.model.enums.LogVideoOperationType;
import com.boot.business.syslog.model.param.LogVideoSaveParam;
import com.boot.business.syslog.service.LogVideoFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "后台-历史视频")
@RestController
@RequestMapping(value = "/sys/history/video")
public class HistoryVideoSysController {

    @Autowired
    private LogVideoFacade logVideoFacade;

    @ApiOperation("保存视频记录")
    @PostMapping("/{type}/save")
    public void add(@PathVariable VideoType type, @RequestBody HistoryVideoSaveParam param) {
        param.setVideoType(type);
        new HistoryVideo().warpT(param).insert();
        logVideoFacade.saveLog(new LogVideoSaveParam(LogVideoOperationType.ADD,
                System.currentTimeMillis(),
                param.getFileId(),
                param.getVideoName(),
                param.getVideoDuration(),
                param.getStartTime()));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除视频")
    public void getById(@PathVariable Long id) {
        HistoryVideo historyVideo = new HistoryVideo().selectById(id);
        if (null == historyVideo) {
            return;
        }
        new HistoryVideo().deleteById(id);
        logVideoFacade.saveLog(new LogVideoSaveParam(LogVideoOperationType.DEL,
                System.currentTimeMillis(),
                historyVideo.getFileId(),
                historyVideo.getVideoName(),
                historyVideo.getVideoDuration(),
                historyVideo.getStartTime()));
    }

    @ApiOperation("获取视频列表(分页)")
    @PostMapping("/{type}/page")
    public IPage<HistoryVideoDTO> page(@PathVariable VideoType type, @RequestBody HistoryVideoPageParam param) {
        return new HistoryVideo().selectPage(param.page(), Wrappers.<HistoryVideo>lambdaQuery()
                .eq(HistoryVideo::getVideoType, type)
                .like(StrUtil.isNotBlank(param.getVideoNameLike()), HistoryVideo::getVideoName, param.getVideoNameLike())
                .ge(param.getUpTimeEnd() != null, HistoryVideo::getCreateTime, param.getUpTimeStart())
                .le(param.getUpTimeEnd() != null, HistoryVideo::getCreateTime, param.getUpTimeEnd()))
                .convert(HistoryVideoDTO::warp);
    }

}
