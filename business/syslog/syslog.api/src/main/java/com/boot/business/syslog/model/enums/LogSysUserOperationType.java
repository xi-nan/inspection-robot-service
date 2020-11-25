package com.boot.business.syslog.model.enums;

/**
 * LogSysUserOperationType
 * 用户日志事件类型
 *
 * @author xinan
 * @date 2020/11/8
 */
public enum LogSysUserOperationType {

    ADD_USER("新增账号"),
    MODIFY_USER("修改用户信息"),
    UPD_PASSWORD("修改密码"),
    RESET_PASSWORD("重置密码"),
    DEL_USER("删除账号");

    private final String desc;

    LogSysUserOperationType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
