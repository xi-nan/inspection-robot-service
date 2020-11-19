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
@Table(appliesTo = "inspection_video", comment = "巡检视频记录")
public class InspectionVideo extends BasePo<InspectionVideo> {

    @ApiModelProperty(value = "巡检编号")
    @Column(columnDefinition = "BIGINT(20) COMMENT '巡检编号'")
    private Long inspectionId;

    @ApiModelProperty(value = "视频类型")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '视频类型'")
    private VideoType videoType;

    @ApiModelProperty(value = "视频文件ID")
    @Column(columnDefinition = "BIGINT(20) COMMENT '视频文件ID'")
    private Long fileId;

}
