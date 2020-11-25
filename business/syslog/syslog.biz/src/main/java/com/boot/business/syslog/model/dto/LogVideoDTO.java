package com.boot.business.syslog.model.dto;

import com.boot.business.historicaldata.model.enums.VideoType;
import com.boot.business.syslog.model.enums.LogVideoOperationType;
import com.boot.business.syslog.model.po.LogVideo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author XINAN
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogVideoDTO {

    @ApiModelProperty(value = "日志记录时间")
    private Long logTime;

    @ApiModelProperty(value = "视频名称")
    private String videoName;

    @ApiModelProperty(value = "视频时长")
    private String videoDuration;

    @ApiModelProperty(value = "操作用户")
    private String creator;

    @ApiModelProperty(value = "操作类型")
    private LogVideoOperationType operationType;

    @ApiModelProperty(value = "操作类型中文描述")
    private String operationTypeStr;

    @ApiModelProperty(value = "视频类型")
    private VideoType videoType;

    @ApiModelProperty(value = "视频类型中文描述")
    private String videoTypeStr;

    public void setOperationType(LogVideoOperationType operationType) {
        this.operationType = operationType;
        this.operationTypeStr = null == operationType ? "" : operationType.getDesc();
    }

    public void setVideoType(VideoType videoType) {
        this.videoType = videoType;
        this.videoTypeStr = null == operationType ? "" : videoType.getDesc();
    }

    public static LogVideoDTO warp(LogVideo po) {
        if (po == null) {
            return null;
        }
        return po.warpR(LogVideoDTO.class);
    }
}
