package com.boot.business.sysuser.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AdminDTO
 *
 * @author XINAN
 * @date 2019/7/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "账号名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "所属角色ID")
    private Long roleId;

    @ApiModelProperty(value = "所属角色名称")
    private String roleName;

    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;

    @ApiModelProperty(value = "创建时间，时间戳 毫秒")
    private Long createTime;

    @ApiModelProperty(value = "手机")
    private String mobile;

}