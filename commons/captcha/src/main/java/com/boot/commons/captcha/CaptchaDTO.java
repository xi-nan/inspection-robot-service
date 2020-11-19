package com.boot.commons.captcha;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CaptchaDTO
 *
 * @author XINAN
 * @date 2020/1/2
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaDTO {

    String token;
    String code;
}
