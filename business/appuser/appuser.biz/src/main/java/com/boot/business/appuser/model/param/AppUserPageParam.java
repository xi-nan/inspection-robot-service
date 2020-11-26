package com.boot.business.appuser.model.param;

import com.boot.commons.core.model.param.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * AppUserPageParam
 *
 * @author XINAN
 * @date 2019/8/3
 */
@Data
public class AppUserPageParam extends PageParam {

    @ApiModelProperty(value = "用户名")
    String usernameLike;

    @ApiModelProperty(value = "手机")
    String mobileLike;

}