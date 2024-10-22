package com.boot.business.syslog.model.dto;

import com.boot.business.syslog.model.enums.LogAlarmType;
import com.boot.business.syslog.model.po.LogAlarm;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author XINAN
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogAlarmDTO {

    @ApiModelProperty(value = "设备ID")
    private Long equipmentId;

    @ApiModelProperty(value = "日志记录时间")
    private Long logTime;

    @ApiModelProperty(value = "报警类型")
    private LogAlarmType alarmType;

    @ApiModelProperty(value = "报警位置")
    private String alarmTypeStr;

    @ApiModelProperty(value = "报警位置中文描述")
    private String location;

    @ApiModelProperty(value = "日志内容")
    private String content;

    public void setAlarmType(LogAlarmType alarmType) {
        this.alarmType = alarmType;
        this.alarmTypeStr = null == alarmType ? "" : alarmType.getDesc();
    }

    public static LogAlarmDTO warp(LogAlarm po) {
        if (po == null) {
            return null;
        }
        return po.warpR(LogAlarmDTO.class);
    }
}
