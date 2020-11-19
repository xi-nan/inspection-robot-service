package com.boot.business.sysuser.model.param;

import com.boot.commons.core.model.param.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * SysUserPageParam
 *
 * @author XINAN
 * @date 2019/8/3
 */
@Data
public class SysUserPageParam extends PageParam {

    @ApiModelProperty(value = "用户名")
    String usernameLike;

    @ApiModelProperty(value = "所属角色ID")
    private Long roleId;

    // @ApiModelProperty(value = "姓名")
    // String nickNameLike;

}