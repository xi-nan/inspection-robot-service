package com.boot.commons.captcha;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CaptchaController
 *
 * @author XINAN
 * @date 2019/12/21
 */

@Api(tags = "验证码")
@RestController
@RequestMapping("/base/captcha")
public class CaptchaController {

    @ApiOperation("生成图形一对验证码, 返回imageBase64")
    @GetMapping("/img/base64")
    public CaptchaDTO imgCaptcha() {
        return CaptchaUtil.genImgCaptcha();
    }

}
