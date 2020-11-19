package com.boot.business.syscode.model.enums;

import com.boot.commons.core.exception.enums.IErrCodeEnum;

/**
 * SysCodeErrCodeEnum
 *
 * @author XINAN
 * @date 2020/6/5
 */
public enum SysCodeErrCodeEnum implements IErrCodeEnum {

    E_20300(20300, "{}");

    private final Integer code;
    private final String desc;

    SysCodeErrCodeEnum(int code, String desc) {
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