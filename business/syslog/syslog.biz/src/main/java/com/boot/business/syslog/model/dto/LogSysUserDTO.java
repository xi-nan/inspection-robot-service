package com.boot.business.syslog.model.dto;

import com.boot.business.syslog.model.enums.LogSysUserOperationType;
import com.boot.business.syslog.model.po.LogSysUser;
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
public class LogSysUserDTO {

    @ApiModelProperty(value = "日志记录时间")
    private Long logTime;

    @ApiModelProperty(value = "日志内容")
    private String content;

    @ApiModelProperty(value = "操作类型")
    private LogSysUserOperationType operationType;


    public static LogSysUserDTO warp(LogSysUser po) {
        if (po == null) {
            return null;
        }
        return po.warpR(LogSysUserDTO.class);
    }
}
