package com.boot.business.syscode.model.param;

import com.boot.commons.core.model.param.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InspectionTrackPageParam extends PageParam {

    @ApiModelProperty(value = "搜索")
    private String trackName;

}

