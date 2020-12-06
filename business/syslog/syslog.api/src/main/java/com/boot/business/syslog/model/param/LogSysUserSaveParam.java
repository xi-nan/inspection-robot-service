package com.boot.business.syslog.model.param;

import com.boot.business.syslog.model.enums.LogSysUserOperationType;
import com.boot.commons.core.security.UserType;
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
public class LogSysUserSaveParam {
    @ApiModelProperty(value = "日志记录时间")
    private Long logTime;

    @ApiModelProperty(value = "日志内容")
    private String content;

    @ApiModelProperty(value = "用户类型")
    private UserType userType;

    @ApiModelProperty(value = "操作类型")
    private LogSysUserOperationType operationType;

    public LogSysUserSaveParam(String content, UserType userType, LogSysUserOperationType operationType) {
        this.content = content;
        this.userType = userType;
        this.operationType = operationType;
    }
}
