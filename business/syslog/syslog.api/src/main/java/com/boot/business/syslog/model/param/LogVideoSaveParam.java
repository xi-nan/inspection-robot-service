package com.boot.business.syslog.model.param;

import com.boot.business.historicaldata.model.enums.VideoType;
import com.boot.business.syslog.model.enums.LogVideoOperationType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author XINAN
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogVideoSaveParam {

    @ApiModelProperty(value = "日志记录时间")
    private Long logTime;

    @ApiModelProperty(value = "操作类型")
    private LogVideoOperationType operationType;

    @ApiModelProperty(value = "设备ID")
    @NotNull(message = "设备ID不可为空")
    private Long equipmentId;

    @ApiModelProperty(value = "视频文件ID")
    private Long fileId;

    @ApiModelProperty(value = "视频名称")
    private String videoName;

    @ApiModelProperty(value = "视频类型")
    private VideoType videoType;

    @ApiModelProperty(value = "视频时长")
    private String videoDuration;

    @ApiModelProperty(value = "开始录制时间")
    private Long startTime;

}
