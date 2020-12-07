package com.boot.business.historicaldata.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.boot.business.historicaldata.model.dto.HistoryInspectionDTO;
import com.boot.business.historicaldata.model.dto.InspectionBatteryDTO;
import com.boot.business.historicaldata.model.dto.InspectionEnvironmentDTO;
import com.boot.business.historicaldata.model.dto.InspectionSpeedDTO;
import com.boot.business.historicaldata.model.enums.TrajectoryType;
import com.boot.business.historicaldata.model.enums.VideoType;
import com.boot.business.historicaldata.model.param.HistoryInspectionPageParam;
import com.boot.business.historicaldata.model.po.*;
import com.boot.commons.localfile.service.LocalFileFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Api(tags = "后台-历史巡检相关")
@RestController
@RequestMapping(value = "/sys/history/inspection")
public class InspectionSysController {

    @ApiOperation("获取巡检列表")
    @PostMapping("/page")
    public IPage<HistoryInspectionDTO> page(@RequestBody HistoryInspectionPageParam param) {
        return new HistoryInspection().selectPage(param.page(), Wrappers.<HistoryInspection>lambdaQuery()
                .eq(param.getEquipmentId() != null, HistoryInspection::getEquipmentId, param.getEquipmentId())
                .ge(param.getStartTime() != null, HistoryInspection::getStartTime, param.getStartTime())
                .le(param.getEndTime() != null, HistoryInspection::getStartTime, param.getEndTime()))
                .convert(HistoryInspectionDTO::warp);
    }

    // =======================================================================

    @ApiOperation("获取巡检环境记录")
    @PostMapping("/environment/{inspectionId}/list")
    public List<InspectionEnvironmentDTO> environmentList(@PathVariable Long inspectionId) {
        return new InspectionEnvironment().selectList(Wrappers.<InspectionEnvironment>lambdaQuery()
                .eq(InspectionEnvironment::getInspectionId, inspectionId)
        ).stream().map(InspectionEnvironmentDTO::warp).collect(Collectors.toList());
    }

    // =======================================================================

    @ApiOperation("获取巡检电池记录")
    @PostMapping("/battery/{inspectionId}/list")
    public List<InspectionBatteryDTO> batteryList(@PathVariable Long inspectionId) {
        return new InspectionBattery().selectList(Wrappers.<InspectionBattery>lambdaQuery()
                .eq(InspectionBattery::getInspectionId, inspectionId)
        ).stream().map(InspectionBatteryDTO::warp).collect(Collectors.toList());
    }

    // =======================================================================

    @ApiOperation("获取巡检速度记录")
    @PostMapping("/speed/{inspectionId}/list")
    public List<InspectionSpeedDTO> speedList(@PathVariable Long inspectionId) {
        return new InspectionSpeed().selectList(Wrappers.<InspectionSpeed>lambdaQuery()
                .eq(InspectionSpeed::getInspectionId, inspectionId)
        ).stream().map(InspectionSpeedDTO::warp).collect(Collectors.toList());
    }

    // =======================================================================

    @Autowired
    private LocalFileFacade localFileFacade;

    @ApiOperation("重试所有被中断或执行失败的巡检视频转码操作")
    @PostMapping("/continueRecodeVideo")
    public Map<String, Object> continueRecodeVideo() {
        List<InspectionVideo> list = new InspectionVideo().selectList(Wrappers.<InspectionVideo>lambdaQuery()
                .gt(InspectionVideo::getFileId, 0L)
                .eq(InspectionVideo::getIsRecode, false));
        AtomicInteger successCount = new AtomicInteger();
        for (InspectionVideo video : list) {
            try {
                localFileFacade.recodeVideo(video.getFileId(), newFileId -> {
                    if (null == newFileId) {
                        return false;
                    }
                    if (new InspectionVideo().update(Wrappers.<InspectionVideo>lambdaUpdate()
                            .eq(InspectionVideo::getId, video.getId())
                            .set(InspectionVideo::getFileId, newFileId)
                            .set(InspectionVideo::getIsRecode, true))) {
                        successCount.getAndIncrement();
                        return true;
                    }
                    return false;
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new HashMap<String, Object>() {{
            put("successCount", successCount.get());
            put("notRecodeCount", list.size());
        }};
    }

    @ApiOperation("获取巡检视频,返回文件ID")
    @PostMapping("/video/{inspectionId}/{type}")
    public Long trajectoryInfo(@PathVariable Long inspectionId, @PathVariable VideoType type) {
        InspectionVideo po = new InspectionVideo().selectOne(Wrappers.<InspectionVideo>lambdaQuery()
                .eq(InspectionVideo::getInspectionId, inspectionId)
                .eq(InspectionVideo::getVideoType, type)
                .gt(InspectionVideo::getFileId, 0L)
                .eq(InspectionVideo::getIsRecode, true)
        );
        return null == po ? -1L : po.getFileId();
    }

    // =======================================================================

    @ApiOperation("获取巡检照片列表")
    @PostMapping("/photo/{inspectionId}/list")
    public List<Long> photoList(@PathVariable Long inspectionId) {
        return new InspectionPhoto().selectList(Wrappers.<InspectionPhoto>lambdaQuery()
                .eq(InspectionPhoto::getInspectionId, inspectionId)
        ).stream().map(InspectionPhoto::getFileId).collect(Collectors.toList());
    }

    // =======================================================================

    @ApiOperation("获取巡检轨迹,返回文件ID")
    @PostMapping("/trajectory/{inspectionId}/{type}")
    public Long trajectoryList(@PathVariable Long inspectionId, @PathVariable TrajectoryType type) {
        InspectionTrajectory po = new InspectionTrajectory().selectOne(Wrappers.<InspectionTrajectory>lambdaQuery()
                .eq(InspectionTrajectory::getInspectionId, inspectionId)
                .eq(InspectionTrajectory::getTrajectoryType, type)
        );
        return null == po ? null : po.getFileId();
    }

}
