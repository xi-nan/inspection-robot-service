package com.boot.business.historicaldata.model.param;

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
public class HistoryVideoSaveParam {

    @ApiModelProperty(value = "视频文件ID")
    private Long fileId;

    @ApiModelProperty(value = "视频名称")
    private String videoName;

    @ApiModelProperty(value = "视频时长")
    private String videoDuration;

    @ApiModelProperty(value = "开始录制时间")
    private Long startTime;

}
