package com.boot.business.syslog.model.po;

import com.boot.commons.core.model.po.BasePo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author XINAN
 */

@EqualsAndHashCode(callSuper = true)
@Data

@Entity
@Table(appliesTo = "log_video", comment = "视频日志")
public class LogVideo extends BasePo<LogVideo> {

    @ApiModelProperty(value = "视频文件ID")
    @Column(columnDefinition = "BIGINT(20) COMMENT '视频文件ID'")
    private Long fileId;

    @ApiModelProperty(value = "日志记录时间")
    @Column(columnDefinition = "BIGINT(13) COMMENT '日志记录时间'")
    private Long logTime;

    @ApiModelProperty(value = "视频名称")
    @Column(columnDefinition = "text COMMENT '视频名称'")
    private String videoName;

    @ApiModelProperty(value = "视频时长")
    @Column(columnDefinition = "text COMMENT '视频时长'")
    private String videoDuration;

    @ApiModelProperty(value = "开始录制时间")
    @Column(columnDefinition = "BIGINT(13) COMMENT '开始录制时间'")
    private Long startTime;

}