package com.boot.business.syslog.model.param;

import com.boot.business.syslog.model.enums.LogAlarmType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author XINAN
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogAlarmSaveParam {

    @ApiModelProperty(value = "设备ID")
    @NotNull(message = "设备ID不可为空")
    private Long equipmentId;

    @ApiModelProperty(value = "日志记录时间")
    @NotNull(message = "日志记录时间不可为空")
    private Long logTime;

    @ApiModelProperty(value = "报警类型")
    private LogAlarmType alarmType;

    @ApiModelProperty(value = "报警位置")
    private String location;

    @ApiModelProperty(value = "日志内容")
    private String content;

}
