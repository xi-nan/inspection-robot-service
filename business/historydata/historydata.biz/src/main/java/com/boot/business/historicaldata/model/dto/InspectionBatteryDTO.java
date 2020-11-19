package com.boot.business.historicaldata.model.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.boot.business.historicaldata.model.po.InspectionBattery;
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
public class InspectionBatteryDTO {

    @ExcelProperty(index = 0, value = "数值记录时间")
    @ApiModelProperty(value = "数值记录时间")
    private Long logTime;

    @ExcelProperty(index = 1, value = "当前电量")
    @ApiModelProperty(value = "当前电量")
    private String electricity;

    @ExcelProperty(index = 2, value = "电压")
    @ApiModelProperty(value = "电压")
    private String voltage;

    @ExcelProperty(index = 3, value = "电流")
    @ApiModelProperty(value = "电流")
    private String electricCurrent;

    @ExcelProperty(index = 4, value = "可用时间")
    @ApiModelProperty(value = "可用时间")
    private String availableTime;

    public static InspectionBatteryDTO warp(InspectionBattery po) {
        if (po == null) {
            return null;
        }
        return po.warpR(InspectionBatteryDTO.class);
    }
}
