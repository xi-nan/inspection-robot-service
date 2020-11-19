package com.boot.business.sysuser.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * SysUserResetPwdParam
 *
 * @author XINAN
 * @date 2019/7/22
 */
@Data
public class SysUserResetPwdParam {

    @ApiModelProperty(value = "旧密码", required = true)
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;

    @ApiModelProperty(value = "新密码", required = true)
    @NotBlank(message = "新密码不能为空")
    // @Pattern(regexp = PatternConstants.STRONG_PASSWORD_2, message = "密码必须包含字母+数字+特殊字符的组合，可用特殊字符 _!@#$%^&* ，长度8位以上")
    private String newPassword;

}