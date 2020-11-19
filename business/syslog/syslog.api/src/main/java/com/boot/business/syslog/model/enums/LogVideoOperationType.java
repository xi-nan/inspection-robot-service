package com.boot.business.syslog.model.enums;

/**
 * LogSysUserOperationType
 * 用户日志事件类型
 *
 * @author xinan
 * @date 2020/11/8
 */
public enum LogVideoOperationType {

    ADD("新增视频"),
    DEL("删除视频");

    public final String desc;

    LogVideoOperationType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
