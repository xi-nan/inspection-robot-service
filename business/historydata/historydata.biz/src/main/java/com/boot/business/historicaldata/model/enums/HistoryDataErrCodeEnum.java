package com.boot.business.historicaldata.model.enums;

import com.boot.commons.core.exception.enums.IErrCodeEnum;

/**
 * HistoryDataErrCodeEnum
 *
 * @author XINAN
 */
public enum HistoryDataErrCodeEnum implements IErrCodeEnum {

    E_29400(29400, "文件内容为空.{}"),
    E_29401(29401, "文件解析失败.{}"),
    E_29402(29401, "数据保存失败.{}");

    private final Integer code;
    private final String desc;

    HistoryDataErrCodeEnum(int code, String desc) {
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