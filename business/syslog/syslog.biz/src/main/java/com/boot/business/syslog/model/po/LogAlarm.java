package com.boot.business.syslog.model.po;

import com.boot.business.syslog.model.enums.LogAlarmType;
import com.boot.commons.core.model.po.BasePo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author XINAN
 */

@EqualsAndHashCode(callSuper = true)
@Data

@Entity
@Table(appliesTo = "log_alarm", comment = "报警日志")
public class LogAlarm extends BasePo<LogAlarm> {

    @ApiModelProperty(value = "设备ID")
    @Column(columnDefinition = "BIGINT(20) COMMENT '设备ID'")
    private Long equipmentId;

    @ApiModelProperty(value = "日志记录时间")
    @Column(columnDefinition = "BIGINT(13) COMMENT '日志记录时间'")
    private Long logTime;

    @ApiModelProperty(value = "报警类型")
    @Column(columnDefinition = "text COMMENT '报警类型'")
    private LogAlarmType alarmType;

    @ApiModelProperty(value = "报警位置")
    @Column(columnDefinition = "text COMMENT '报警位置'")
    private String location;

    @ApiModelProperty(value = "日志内容")
    @Column(columnDefinition = "text COMMENT '日志内容'")
    private String content;

}
