package com.boot.commons.captcha.param;

import com.boot.commons.captcha.CaptchaUtil;
import com.boot.commons.core.constants.PatternConstants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
public class SmsValidCodeParam {

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = PatternConstants.MOBILE, message = "手机号格式错误!")//必须校验手机号格式，避免被使用图形验证码token+code通过校验
    protected String mobile;

    @ApiModelProperty(value = "验证码")
    @NotBlank(message = "验证码不能为空")
    protected String validCode;

    @AssertTrue(message = "验证码错误!")
    private boolean isValidCodeTrue() {
        return CaptchaUtil.verify(this.mobile, this.validCode, false);
    }
}
