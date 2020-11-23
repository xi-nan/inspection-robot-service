package com.boot.business.syslog.model.param;

import com.boot.business.syslog.model.enums.EquipmentStatus;
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
public class LogEquipmentRunSaveParam {

    @ApiModelProperty(value = "日志记录时间")
    @NotNull(message = "日志记录时间不可为空")
    private Long logTime;

    @ApiModelProperty(value = "设备ID")
    @NotNull(message = "设备ID不可为空")
    private Long equipmentId;

    @ApiModelProperty(value = "设备状态")
    private EquipmentStatus equipmentStatus;

    @ApiModelProperty(value = "电池信息")
    private String batteryInformation;

    @ApiModelProperty(value = "温度")
    private String temperature;

    @ApiModelProperty(value = "湿度")
    private String humidity;

    @ApiModelProperty(value = "甲烷")
    private String methane;

    @ApiModelProperty(value = "乙烷")
    private String ethane;

    @ApiModelProperty(value = "丙烷")
    private String propane;

    @ApiModelProperty(value = "丁烷")
    private String butane;

    @ApiModelProperty(value = "一氧化碳")
    private String carbonMonoxide;

    @ApiModelProperty(value = "硫化氢")
    private String hydrogenSulfide;
}
