package com.boot.business.syscode.model.param;

import com.boot.commons.core.model.param.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InspectionTrackPageParam extends PageParam {

    @ApiModelProperty(value = "轨迹名称")
    private String trackName;

    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;

    @ApiModelProperty(value = "创建时间筛选-开始")
    private Long createTimeStart;

    @ApiModelProperty(value = "创建时间筛选-结束")
    private Long createTimeEnd;

}

