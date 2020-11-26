package com.boot.business.appuser.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AppUserDTO
 *
 * @author XINAN
 * @date 2019/7/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "账号名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;

    @ApiModelProperty(value = "创建时间，时间戳 毫秒")
    private Long createTime;

}