package com.boot.business.sysuser.model.param;

import com.boot.commons.core.model.param.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * RolePageParam
 *
 * @author XINAN
 * @date 2019/8/3
 */
@Data
public class RolePageParam extends PageParam {

    @ApiModelProperty(value = "名称")
    String nameLike;

}