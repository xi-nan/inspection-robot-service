package com.boot.business.historicaldata.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.boot.business.historicaldata.listener.SimpImportListener;
import com.boot.business.historicaldata.model.dto.HistoryInspectionDTO;
import com.boot.business.historicaldata.model.dto.InspectionBatteryDTO;
import com.boot.business.historicaldata.model.dto.InspectionEnvironmentDTO;
import com.boot.business.historicaldata.model.dto.InspectionSpeedDTO;
import com.boot.business.historicaldata.model.enums.HistoryDataErrCodeEnum;
import com.boot.business.historicaldata.model.enums.TrajectoryType;
import com.boot.business.historicaldata.model.enums.VideoType;
import com.boot.business.historicaldata.model.param.HistoryInspectionPageParam;
import com.boot.business.historicaldata.model.po.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(tags = "客户端-历史巡检相关")
@RestController
@RequestMapping(value = "/app/history/inspection")
public class InspectionAppController {

    @ApiOperation("创建巡检记录")
    @PostMapping("/save/{equipmentId}")
    public Long save(@PathVariable Long equipmentId, @RequestParam Long startTime, @RequestParam Long endTime) {

        HistoryInspection inspection = new HistoryInspection().selectOne(Wrappers.<HistoryInspection>lambdaQuery()
                .eq(HistoryInspection::getEquipmentId, equipmentId)
                .eq(HistoryInspection::getStartTime, startTime));
        if (null != inspection) {
            return inspection.getId();
        }
        HistoryInspection save = new HistoryInspection();
        save.setEquipmentId(equipmentId);
        save.setStartTime(startTime);
        save.setEndTime(endTime);
        save.insert();
        return save.getId();
    }

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

    @ApiOperation("上传巡检环境记录")
    @PostMapping("/environment/{inspectionId}/save")
    public void environmentSave(@PathVariable Long inspectionId, MultipartFile file) {
        SimpImportListener<InspectionEnvironmentDTO> excelListener = new SimpImportListener<>();
        InputStream fileStream = null;
        try {
            fileStream = file.getInputStream();
            EasyExcel.read(file.getInputStream(), InspectionEnvironmentDTO.class, excelListener).sheet().doRead();
            HistoryDataErrCodeEnum.E_29400.throwIf(excelListener.getDatas().isEmpty() || excelListener.getDatas().size() < 1);
            // 删除旧数据
            new InspectionEnvironment().delete(Wrappers.<InspectionEnvironment>lambdaQuery()
                    .eq(InspectionEnvironment::getInspectionId, inspectionId));
            // 插入新数据
            excelListener.getDatas().forEach(dto -> {
                InspectionEnvironment po = new InspectionEnvironment().warpT(dto);
                po.setInspectionId(inspectionId);
                po.insert();
            });
        } catch (Exception e) {
            HistoryDataErrCodeEnum.E_29402.throwThis(e.toString());
        } finally {
            try {
                if (fileStream != null) {
                    fileStream.close();
                }
            } catch (IOException e) {
                log.error("excel文件读取失败, 失败原因：" + e);
            }
        }
    }

    @ApiOperation("获取巡检环境记录")
    @PostMapping("/environment/{inspectionId}/list")
    public List<InspectionEnvironmentDTO> environmentList(@PathVariable Long inspectionId) {
        return new InspectionEnvironment().selectList(Wrappers.<InspectionEnvironment>lambdaQuery()
                .eq(InspectionEnvironment::getInspectionId, inspectionId)
        ).stream().map(InspectionEnvironmentDTO::warp).collect(Collectors.toList());
    }

    // =======================================================================

    @ApiOperation("上传巡检电池记录")
    @PostMapping("/battery/{inspectionId}/save")
    public void batterySave(@PathVariable Long inspectionId, MultipartFile file) {
        SimpImportListener<InspectionBatteryDTO> excelListener = new SimpImportListener<>();
        InputStream fileStream = null;
        try {
            fileStream = file.getInputStream();
            EasyExcel.read(file.getInputStream(), InspectionBatteryDTO.class, excelListener).sheet().doRead();
            HistoryDataErrCodeEnum.E_29400.throwIf(excelListener.getDatas().isEmpty() || excelListener.getDatas().size() < 1);
            // 删除旧数据
            new InspectionBattery().delete(Wrappers.<InspectionBattery>lambdaQuery().eq(InspectionBattery::getInspectionId, inspectionId));
            // 插入新数据
            excelListener.getDatas().forEach(dto -> {
                InspectionBattery po = new InspectionBattery().warpT(dto);
                po.setInspectionId(inspectionId);
                po.insert();
            });
        } catch (Exception e) {
            HistoryDataErrCodeEnum.E_29402.throwThis(e.toString());
        } finally {
            try {
                if (fileStream != null) {
                    fileStream.close();
                }
            } catch (IOException e) {
                log.error("excel文件读取失败, 失败原因：" + e);
            }
        }
    }

    @ApiOperation("获取巡检电池记录")
    @PostMapping("/battery/{inspectionId}/list")
    public List<InspectionBatteryDTO> batteryList(@PathVariable Long inspectionId) {
        return new InspectionBattery().selectList(Wrappers.<InspectionBattery>lambdaQuery()
                .eq(InspectionBattery::getInspectionId, inspectionId)
        ).stream().map(InspectionBatteryDTO::warp).collect(Collectors.toList());
    }

    // =======================================================================

    @ApiOperation("上传巡检速度记录")
    @PostMapping("/speed/{inspectionId}/save")
    public void speedSave(@PathVariable Long inspectionId, MultipartFile file) {
        SimpImportListener<InspectionSpeedDTO> excelListener = new SimpImportListener<>();
        InputStream fileStream = null;
        try {
            fileStream = file.getInputStream();
            EasyExcel.read(file.getInputStream(), InspectionSpeedDTO.class, excelListener).sheet().doRead();
            HistoryDataErrCodeEnum.E_29400.throwIf(excelListener.getDatas().isEmpty() || excelListener.getDatas().size() < 1);
            // 删除旧数据
            new InspectionSpeed().delete(Wrappers.<InspectionSpeed>lambdaQuery()
                    .eq(InspectionSpeed::getInspectionId, inspectionId));
            // 插入新数据
            excelListener.getDatas().forEach(dto -> {
                InspectionSpeed po = new InspectionSpeed().warpT(dto);
                po.setInspectionId(inspectionId);
                po.insert();
            });
        } catch (Exception e) {
            HistoryDataErrCodeEnum.E_29402.throwThis(e.toString());
        } finally {
            try {
                if (fileStream != null) {
                    fileStream.close();
                }
            } catch (IOException e) {
                log.error("excel文件读取失败, 失败原因：" + e);
            }
        }
    }

    @ApiOperation("获取巡检速度记录")
    @PostMapping("/speed/{inspectionId}/list")
    public List<InspectionSpeedDTO> speedList(@PathVariable Long inspectionId) {
        return new InspectionSpeed().selectList(Wrappers.<InspectionSpeed>lambdaQuery()
                .eq(InspectionSpeed::getInspectionId, inspectionId)
        ).stream().map(InspectionSpeedDTO::warp).collect(Collectors.toList());
    }

    // =======================================================================

    @ApiOperation("保存巡检视频")
    @PostMapping("/video/{inspectionId}/save")
    public void videoSave(@PathVariable Long inspectionId, @RequestParam Long visibleVideoFileId, @RequestParam Long thermographyVideoFileId) {
        // 删除旧数据
        new InspectionVideo().delete(Wrappers.<InspectionVideo>lambdaQuery()
                .eq(InspectionVideo::getInspectionId, inspectionId));
        // 插入新数据
        InspectionVideo visible = new InspectionVideo();
        visible.setInspectionId(inspectionId);
        visible.setVideoType(VideoType.VISIBLE);
        visible.setFileId(visibleVideoFileId);
        visible.insert();
        InspectionVideo thermography = new InspectionVideo();
        thermography.setInspectionId(inspectionId);
        thermography.setVideoType(VideoType.THERMOGRAPHY);
        thermography.setFileId(thermographyVideoFileId);
        thermography.insert();
    }

    @ApiOperation("获取巡检视频,返回文件ID")
    @PostMapping("/video/{inspectionId}/{type}")
    public Long trajectoryInfo(@PathVariable Long inspectionId, @PathVariable VideoType type) {
        InspectionVideo po = new InspectionVideo().selectOne(Wrappers.<InspectionVideo>lambdaQuery()
                .eq(InspectionVideo::getInspectionId, inspectionId)
                .eq(InspectionVideo::getVideoType, type)
        );
        return null == po ? null : po.getFileId();
    }

    // =======================================================================

    @ApiOperation("保存巡检照片")
    @PostMapping("/photo/{inspectionId}/save")
    public void photoSave(@PathVariable Long inspectionId, @RequestParam Long[] fileIds) {
        // 删除旧数据
        new InspectionPhoto().delete(Wrappers.<InspectionPhoto>lambdaQuery()
                .eq(InspectionPhoto::getInspectionId, inspectionId));
        // 插入新数据
        InspectionPhoto photo = new InspectionPhoto();
        photo.setInspectionId(inspectionId);
        Arrays.stream(fileIds).forEach(it -> {
            photo.setFileId(it);
            photo.insert();
        });
    }

    @ApiOperation("获取巡检照片列表")
    @PostMapping("/photo/{inspectionId}/list")
    public List<Long> photoList(@PathVariable Long inspectionId) {
        return new InspectionPhoto().selectList(Wrappers.<InspectionPhoto>lambdaQuery()
                .eq(InspectionPhoto::getInspectionId, inspectionId)
        ).stream().map(InspectionPhoto::getFileId).collect(Collectors.toList());
    }

    // =======================================================================

    @ApiOperation("保存巡检轨迹")
    @PostMapping("/trajectory/{inspectionId}/save")
    public void trajectorySave(@PathVariable Long inspectionId, @RequestParam Long actualFileId, @RequestParam Long presetFileId) {
        // 删除旧数据
        new InspectionTrajectory().delete(Wrappers.<InspectionTrajectory>lambdaQuery()
                .eq(InspectionTrajectory::getInspectionId, inspectionId));
        // 插入新数据
        InspectionTrajectory actual = new InspectionTrajectory();
        actual.setInspectionId(inspectionId);
        actual.setTrajectoryType(TrajectoryType.ACTUAL);
        actual.setFileId(actualFileId);
        actual.insert();
        InspectionTrajectory preset = new InspectionTrajectory();
        preset.setInspectionId(inspectionId);
        preset.setTrajectoryType(TrajectoryType.PRESET);
        preset.setFileId(presetFileId);
        preset.insert();
    }

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
