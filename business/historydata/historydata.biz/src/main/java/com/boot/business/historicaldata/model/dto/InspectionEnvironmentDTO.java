package com.boot.business.historicaldata.model.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.boot.business.historicaldata.model.po.InspectionEnvironment;
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
public class InspectionEnvironmentDTO {

    @ExcelProperty(index = 0, value = "数值记录时间")
    @ApiModelProperty(value = "数值记录时间")
    private Long logTime;

    @ExcelProperty(index = 1, value = "温度")
    @ApiModelProperty(value = "温度")
    private String temperature;

    @ExcelProperty(index = 2, value = "湿度")
    @ApiModelProperty(value = "湿度")
    private String humidity;

    @ExcelProperty(index = 3, value = "甲烷")
    @ApiModelProperty(value = "甲烷")
    private String methane;

    @ExcelProperty(index = 4, value = "乙烷")
    @ApiModelProperty(value = "乙烷")
    private String ethane;

    @ExcelProperty(index = 5, value = "丙烷")
    @ApiModelProperty(value = "丙烷")
    private String propane;

    @ExcelProperty(index = 6, value = "丁烷")
    @ApiModelProperty(value = "丁烷")
    private String butane;

    @ExcelProperty(index = 7, value = "一氧化碳")
    @ApiModelProperty(value = "一氧化碳")
    private String carbonMonoxide;

    @ExcelProperty(index = 8, value = "硫化氢")
    @ApiModelProperty(value = "硫化氢")
    private String hydrogenSulfide;

    public static InspectionEnvironmentDTO warp(InspectionEnvironment po) {
        if (po == null) {
            return null;
        }
        return po.warpR(InspectionEnvironmentDTO.class);
    }
}
