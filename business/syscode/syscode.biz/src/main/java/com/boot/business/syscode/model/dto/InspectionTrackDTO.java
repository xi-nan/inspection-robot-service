package com.boot.business.syscode.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InspectionTrackDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "轨迹名称")
    private String trackName;

    @ApiModelProperty(value = "坐标数据")
    private String coordinateData;

    @ApiModelProperty(value = "RFID数据")
    private String rfidData;

    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;
}
