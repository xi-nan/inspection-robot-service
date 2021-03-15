package com.boot.business.historicaldata.model.dto;

import com.boot.business.historicaldata.model.po.HistoryInspection;
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
public class HistoryInspectionDTO {

    @ApiModelProperty(value = "巡检ID")
    private Long id;

    @ApiModelProperty(value = "设备ID")
    private Long equipmentId;

    @ApiModelProperty(value = "巡检开始时间")
    private Long startTime;

    @ApiModelProperty(value = "巡检结束时间")
    private Long endTime;


    public static HistoryInspectionDTO warp(HistoryInspection po) {
        if (po == null) {
            return null;
        }
        return po.warpR(HistoryInspectionDTO.class);
    }
}
