package com.boot.business.syslog.model.dto;

import com.boot.business.syslog.model.enums.LogSysUserOperationType;
import com.boot.business.syslog.model.po.LogSysUser;
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
public class LogSysUserDTO {

    @ApiModelProperty(value = "日志记录时间")
    private Long logTime;

    @ApiModelProperty(value = "日志内容")
    private String content;

    @ApiModelProperty(value = "用户类型")
    private UserType userType;

    @ApiModelProperty(value = "用户类型中文描述")
    private String userTypeStr;

    @ApiModelProperty(value = "操作类型")
    private LogSysUserOperationType operationType;

    @ApiModelProperty(value = "操作类型中文描述")
    private String operationTypeStr;

    @ApiModelProperty(value = "操作用户")
    private String creator;

    public void setUserType(UserType userType) {
        this.userType = userType;
        this.userTypeStr = null == userType ? "" : userType.getDesc();
    }

    public void setOperationType(LogSysUserOperationType operationType) {
        this.operationType = operationType;
        this.operationTypeStr = null == operationType ? "" : operationType.getDesc();
    }

    public static LogSysUserDTO warp(LogSysUser po) {
        if (po == null) {
            return null;
        }
        return po.warpR(LogSysUserDTO.class);
    }
}
