package com.boot.commons.captcha.param;

import com.boot.commons.captcha.CaptchaUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class ImgValidCodeParam {

    @ApiModelProperty(value = "验证码token")
    @NotBlank(message = "验证码token不能为空")
    protected String validToken;

    @ApiModelProperty(value = "验证码")
    @NotBlank(message = "验证码不能为空")
    protected String validCode;

    @AssertTrue(message = "验证码错误!")
    private boolean isValidCodeTrue() {
        return CaptchaUtil.verify(this.validToken, this.validCode, false);
    }
}
