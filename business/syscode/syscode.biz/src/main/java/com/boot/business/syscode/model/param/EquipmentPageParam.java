package com.boot.business.syscode.model.param;

import com.boot.commons.core.model.param.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EquipmentPageParam extends PageParam {

    @ApiModelProperty(value = "设备ID")
    private Long id;

    @ApiModelProperty(value = "绑定账号搜索(用户名、昵称、手机号)")
    private String userSearch;

}

