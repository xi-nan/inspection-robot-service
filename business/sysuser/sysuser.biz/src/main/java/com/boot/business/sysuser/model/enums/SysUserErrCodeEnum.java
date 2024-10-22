package com.boot.business.sysuser.model.enums;

import com.boot.commons.core.exception.enums.IErrCodeEnum;

/**
 * SysUserErrCodeEnum
 *
 * @author XINAN
 * @date 2019/7/22
 */
public enum SysUserErrCodeEnum implements IErrCodeEnum {

    E_20100(20100, "用户名[{}}]已被使用."),
    E_20101(20101, "重置密码失败，原始密码不正确"),
    E_20102(20102, "未找到ID为 {} 的账号"),
    E_20103(20103, "超级管理员不可删除");

    private final Integer code;
    private final String desc;

    SysUserErrCodeEnum(int code, String desc) {
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