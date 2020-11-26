package com.boot.business.historicaldata.model.po;

import com.boot.business.historicaldata.model.enums.VideoType;
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
@Table(appliesTo = "history_video", comment = "历史视频")
public class HistoryVideo extends BasePo<HistoryVideo> {

    @ApiModelProperty(value = "设备ID")
    @Column(columnDefinition = "BIGINT(20) COMMENT '设备ID'")
    private Long equipmentId;

    @ApiModelProperty(value = "视频文件ID")
    @Column(columnDefinition = "BIGINT(20) COMMENT '视频文件ID'")
    private Long fileId;

    @ApiModelProperty(value = "视频名称")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '视频名称'")
    private String videoName;

    @ApiModelProperty(value = "视频类型")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '视频类型'")
    private VideoType videoType;

    @ApiModelProperty(value = "视频时长")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '视频时长'")
    private String videoDuration;

    @ApiModelProperty(value = "开始录制时间")
    @Column(columnDefinition = "BIGINT(13) COMMENT '开始录制时间'")
    private Long startTime;

}
