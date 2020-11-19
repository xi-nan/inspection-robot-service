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
@Table(appliesTo = "inspection_battery", comment = "巡检电池记录")
public class InspectionBattery extends BasePo<InspectionBattery> {

    @ApiModelProperty(value = "巡检编号")
    @Column(columnDefinition = "BIGINT(20) COMMENT '巡检编号'")
    private Long inspectionId;

    @ApiModelProperty(value = "数值记录时间")
    @Column(columnDefinition = "BIGINT(13) COMMENT '数值记录时间'")
    private Long logTime;

    @ApiModelProperty(value = "当前电量")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '当前电量'")
    private String electricity;

    @ApiModelProperty(value = "电压")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '电压'")
    private String voltage;

    @ApiModelProperty(value = "电流")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '电流'")
    private String electricCurrent;

    @ApiModelProperty(value = "可用时间")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '可用时间'")
    private String availableTime;

}
