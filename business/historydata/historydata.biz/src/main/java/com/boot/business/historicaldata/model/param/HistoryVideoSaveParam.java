package com.boot.business.historicaldata.model.param;

import com.boot.business.historicaldata.model.enums.VideoType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author XINAN
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryVideoSaveParam {

    @NotNull(message = "设备ID不可为空")
    @ApiModelProperty(value = "设备ID")
    private Long equipmentId;

    @NotNull(message = "视频文件ID 不可为空")
    @ApiModelProperty(value = "视频文件ID")
    private Long fileId;

    @ApiModelProperty(value = "视频名称")
    private String videoName;

    @ApiModelProperty(value = "视频时长")
    private String videoDuration;

    @ApiModelProperty(value = "开始录制时间")
    private Long startTime;

    @ApiModelProperty(value = "视频类型", hidden = true)
    private VideoType videoType;

}
