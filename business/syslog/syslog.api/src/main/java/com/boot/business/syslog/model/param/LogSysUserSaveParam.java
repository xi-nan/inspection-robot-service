package com.boot.business.syslog.model.param;

import com.boot.business.syslog.model.enums.LogSysUserOperationType;
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

    @ApiModelProperty(value = "日志内容")
    private String content;

    @ApiModelProperty(value = "操作类型")
    private LogSysUserOperationType operationType;

}
