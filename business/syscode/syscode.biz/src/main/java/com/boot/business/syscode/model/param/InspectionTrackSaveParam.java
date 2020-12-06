package com.boot.business.syscode.model.param;

import com.boot.commons.core.model.param.ValidGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InspectionTrackSaveParam {

    @ApiModelProperty(value = "ID")
    @NotNull(message = "id 不能为空！", groups = {ValidGroup.upd.class})
    @Null(message = "新增数据 id 应为空！", groups = {ValidGroup.add.class})
    private Long id;

    @ApiModelProperty(value = "轨迹名称")
    private String trackName;

    @ApiModelProperty(value = "坐标数据")
    private String coordinateData;

    @ApiModelProperty(value = "RFID数据")
    private String rfidData;

}
