package com.boot.business.historicaldata.model.po;

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
@Table(appliesTo = "inspection_speed", comment = "巡检速度记录")
public class InspectionSpeed extends BasePo<InspectionSpeed> {

    @ApiModelProperty(value = "巡检编号")
    @Column(columnDefinition = "BIGINT(20) COMMENT '巡检编号'")
    private Long inspectionId;

    @ApiModelProperty(value = "数值记录时间")
    @Column(columnDefinition = "BIGINT(13) COMMENT '数值记录时间'")
    private Long logTime;

    @ApiModelProperty(value = "当前速度")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '当前速度'")
    private String speed;


}
