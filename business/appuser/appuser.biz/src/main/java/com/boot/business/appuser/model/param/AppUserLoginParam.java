package com.boot.business.appuser.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * AppUserLoginParam
 *
 * @author XINAN
 * @date 2019/7/22
 */
@Data
public class AppUserLoginParam
//        extends ImgValidCodeParam
{

    @ApiModelProperty(value = "登录名", required = true)
    @NotBlank(message = "登录名不能为空")
    // @Pattern(regexp = PatternConstants.LOGIN_NAME, message = "登录名必须字母开头,允许5-18字节,允许字母数字下划线")
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    // @Pattern(regexp = PatternConstants.STRONG_PASSWORD_2, message = "密码必须包含字母+数字+特殊字符的组合，可用特殊字符 _!@#$%^&* ，长度8位以上")
    private String password;

}