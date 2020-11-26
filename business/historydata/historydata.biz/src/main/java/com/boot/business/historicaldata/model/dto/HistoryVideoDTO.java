package com.boot.business.historicaldata.model.dto;

import com.boot.business.historicaldata.model.enums.VideoType;
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
public class HistoryVideoDTO {

    @ApiModelProperty(value = "视频记录ID")
    private Long id;

    @ApiModelProperty(value = "视频文件ID")
    private Long fileId;

    @ApiModelProperty(value = "视频文件已转码,未转码播放时提示正在转码")
    private Boolean isRecode;

    @ApiModelProperty(value = "视频名称")
    private String videoName;

    @ApiModelProperty(value = "视频类型")
    private VideoType videoType;

    @ApiModelProperty(value = "视频时长")
    private String videoDuration;

    @ApiModelProperty(value = "开始录制时间")
    private Long startTime;

}
