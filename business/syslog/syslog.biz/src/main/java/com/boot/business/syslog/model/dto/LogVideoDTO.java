package com.boot.business.syslog.model.dto;

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

    public static LogVideoDTO warp(LogVideo po) {
        if (po == null) {
            return null;
        }
        return po.warpR(LogVideoDTO.class);
    }
}
