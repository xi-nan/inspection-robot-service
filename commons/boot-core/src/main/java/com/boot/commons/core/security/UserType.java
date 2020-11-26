package com.boot.commons.core.security;

/**
 * UserType
 *
 * @author XINAN
 * @date 2020/6/8
 */
public enum UserType {
    ADMIN("管理员账号"),
    APP("客户端账号");


    private final String desc;

    UserType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
