package com.boot.business.historicaldata.model.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.boot.business.historicaldata.model.po.InspectionSpeed;
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
public class InspectionSpeedDTO {

    @ExcelProperty(index = 0, value = "数值记录时间")
    @ApiModelProperty(value = "数值记录时间")
    private Long logTime;

    @ExcelProperty(index = 1, value = "当前速度")
    @ApiModelProperty(value = "当前速度")
    private String speed;


    public static InspectionSpeedDTO warp(InspectionSpeed po) {
        if (po == null) {
            return null;
        }
        return po.warpR(InspectionSpeedDTO.class);
    }

}
