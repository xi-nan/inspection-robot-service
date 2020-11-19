package com.boot.business.syslog.model.enums;

/**
 * LogAlarmType
 * 设备状态
 *
 * @author xinan
 * @date 2020/11/8
 */
public enum EquipmentStatus {

    STANDBY("待机"),
    CHARGING("充电中"),
    AT_WORK("工作中");

    public final String desc;

    EquipmentStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }


}
