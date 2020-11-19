package com.boot.commons.captcha;

import com.boot.commons.core.exception.enums.IErrCodeEnum;

/**
 * SysUserErrCodeEnum
 *
 * @author XINAN
 * @date 2019/7/22
 */
public enum CaptchaErrCodeEnum implements IErrCodeEnum {

    E_29900(29900, "验证码不正确!"),
    E_29901(29901, "验证码已失效,请重新获取!");

    private final Integer code;
    private final String desc;

    CaptchaErrCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }


}