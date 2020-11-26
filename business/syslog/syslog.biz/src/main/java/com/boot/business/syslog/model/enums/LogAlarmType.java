package com.boot.business.syslog.model.enums;


/**
 * LogAlarmType
 * 报警类型
 *
 * @author xinan
 * @date 2020/11/8
 */
public enum LogAlarmType {

    ROBOT_STATUS("机器人状态异常"),
    ENVIRONMENTAL_PARAMETERS("环境参数异常"),
    CAMERA_MONITORING_ALARM("摄像头监测报警");

    public final String desc;

    LogAlarmType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }


}
