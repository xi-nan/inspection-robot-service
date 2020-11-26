package com.boot.business.historicaldata.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.business.historicaldata.mapper.HistoryVideoMapper;
import com.boot.business.historicaldata.model.dto.HistoryVideoDTO;
import com.boot.business.historicaldata.model.enums.VideoType;
import com.boot.business.historicaldata.model.param.HistoryVideoPageParam;
import com.boot.business.historicaldata.model.param.HistoryVideoSaveParam;
import com.boot.business.historicaldata.model.po.HistoryVideo;
import com.boot.business.syslog.model.enums.LogVideoOperationType;
import com.boot.business.syslog.model.param.LogVideoSaveParam;
import com.boot.business.syslog.service.LogVideoFacade;
import com.boot.commons.localfile.service.LocalFileFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * HistoryVideoService
 *
 * @author XINAN
 * @date 2020/6/12
 */
@Service
public class HistoryVideoService extends ServiceImpl<HistoryVideoMapper, HistoryVideo> {

    @Autowired
    @Lazy
    private HistoryVideoService service;

    @Autowired
    private LogVideoFacade logVideoFacade;

    @Autowired
    private LocalFileFacade localFileFacade;

    public void add(HistoryVideoSaveParam param) {
        HistoryVideo save = new HistoryVideo().warpT(param);
        save.insert();
        service.recodeVideo(save.getId(), param.getFileId());
        logVideoFacade.saveLog(LogVideoSaveParam.builder()
                .operationType(LogVideoOperationType.ADD)
                .equipmentId(param.getEquipmentId())
                .videoType(param.getVideoType())
                .fileId(param.getFileId())
                .videoName(param.getVideoName())
                .videoDuration(param.getVideoDuration())
                .startTime(param.getStartTime())
                .build());
    }

    /**
     * 视频转码
     *
     * @param id
     * @param fileId
     */
    @Async
    public void recodeVideo(Long id, Long fileId) {
        localFileFacade.recodeVideo(fileId, newFileId -> {
            if (null == newFileId) {
                return false;
            }
            return super.lambdaUpdate().eq(HistoryVideo::getId, id)
                    .set(HistoryVideo::getFileId, newFileId)
                    .set(HistoryVideo::getIsRecode, true).update();
        });
    }

    public void del(Long id) {
        HistoryVideo historyVideo = this.getById(id);
        if (null == historyVideo) {
            return;
        }
        new HistoryVideo().deleteById(id);
        logVideoFacade.saveLog(LogVideoSaveParam.builder()
                .operationType(LogVideoOperationType.DEL)
                .videoType(historyVideo.getVideoType())
                .equipmentId(historyVideo.getEquipmentId())
                .fileId(historyVideo.getFileId())
                .videoName(historyVideo.getVideoName())
                .videoDuration(historyVideo.getVideoDuration())
                .startTime(historyVideo.getStartTime())
                .build());
    }

    public IPage<HistoryVideoDTO> page(VideoType type, HistoryVideoPageParam param) {
        return new HistoryVideo().selectPage(param.page(), Wrappers.<HistoryVideo>lambdaQuery()
                .eq(HistoryVideo::getVideoType, type)
                .eq(param.getEquipmentId() != null, HistoryVideo::getEquipmentId, param.getEquipmentId())
                .like(StrUtil.isNotBlank(param.getVideoNameLike()), HistoryVideo::getVideoName, param.getVideoNameLike())
                .ge(param.getUpTimeEnd() != null, HistoryVideo::getCreateTime, param.getUpTimeStart())
                .le(param.getUpTimeEnd() != null, HistoryVideo::getCreateTime, param.getUpTimeEnd()))
                .convert(this::warp);
    }

    public HistoryVideoDTO warp(HistoryVideo po) {
        if (po == null) {
            return null;
        }
        return po.warpR(HistoryVideoDTO.class);
    }
}
