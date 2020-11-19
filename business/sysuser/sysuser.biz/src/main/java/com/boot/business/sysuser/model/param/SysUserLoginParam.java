package com.boot.business.sysuser.model.param;

import com.boot.business.sysuser.model.enums.LoginPlatform;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * AdminLoginParam
 *
 * @author XINAN
 * @date 2019/7/22
 */
@Data
public class SysUserLoginParam
//        extends ImgValidCodeParam
{

    @ApiModelProperty(value = "登录平台", required = true)
    @NotNull(message = "登录平台不能为空")
    private LoginPlatform loginPlatform;

    @ApiModelProperty(value = "登录名", required = true)
    @NotBlank(message = "登录名不能为空")
    // @Pattern(regexp = PatternConstants.LOGIN_NAME, message = "登录名必须字母开头,允许5-18字节,允许字母数字下划线")
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    // @Pattern(regexp = PatternConstants.STRONG_PASSWORD_2, message = "密码必须包含字母+数字+特殊字符的组合，可用特殊字符 _!@#$%^&* ，长度8位以上")
    private String password;

}