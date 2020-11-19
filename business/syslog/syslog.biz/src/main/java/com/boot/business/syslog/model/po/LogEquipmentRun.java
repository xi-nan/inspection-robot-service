package com.boot.business.syslog.model.po;

import com.boot.business.syslog.model.enums.EquipmentStatus;
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
@Table(appliesTo = "log_equipment_run", comment = "设备运行日志")
public class LogEquipmentRun extends BasePo<LogEquipmentRun> {

    @ApiModelProperty(value = "日志记录时间")
    @Column(columnDefinition = "BIGINT(13) COMMENT '日志记录时间'")
    private Long logTime;

    @ApiModelProperty(value = "设备ID")
    @Column(columnDefinition = "BIGINT(20) COMMENT '设备ID'")
    private Long equipmentId;

    @ApiModelProperty(value = "设备状态")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '设备状态'")
    private EquipmentStatus equipmentStatus;

    @ApiModelProperty(value = "电池信息")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '电池信息'")
    private String batteryInformation;

    @ApiModelProperty(value = "温度")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '温度'")
    private String temperature;

    @ApiModelProperty(value = "湿度")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '湿度'")
    private String humidity;

    @ApiModelProperty(value = "甲烷")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '甲烷'")
    private String methane;

    @ApiModelProperty(value = "乙烷")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '乙烷'")
    private String ethane;

    @ApiModelProperty(value = "丙烷")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '丙烷'")
    private String propane;

    @ApiModelProperty(value = "丁烷")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '丁烷'")
    private String butane;

    @ApiModelProperty(value = "一氧化碳")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '一氧化碳'")
    private String carbonMonoxide;

    @ApiModelProperty(value = "硫化氢")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '硫化氢'")
    private String hydrogenSulfide;
}
