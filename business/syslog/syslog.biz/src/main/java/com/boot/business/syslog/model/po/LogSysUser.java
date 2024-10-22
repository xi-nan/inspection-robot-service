package com.boot.business.syslog.model.po;

import com.boot.business.syslog.model.enums.LogSysUserOperationType;
import com.boot.commons.core.model.po.BasePo;
import com.boot.commons.core.security.UserType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author XINAN
 */

@EqualsAndHashCode(callSuper = true)
@Data

@Entity
@Table(appliesTo = "log_sys_user", comment = "用户日志", indexes = {
        @Index(name = "IX_LogSysUser_deleted", columnNames = {"deleted"})
})
public class LogSysUser extends BasePo<LogSysUser> {

    @ApiModelProperty(value = "日志记录时间")
    @Column(columnDefinition = "BIGINT(13) COMMENT '日志记录时间'")
    private Long logTime;

    @ApiModelProperty(value = "日志内容")
    @Column(columnDefinition = "text COMMENT '日志内容'")
    private String content;

    @ApiModelProperty(value = "操作类型")
    @Column(columnDefinition = "text COMMENT '操作类型'")
    private LogSysUserOperationType operationType;

    @ApiModelProperty(value = "用户类型")
    @Column(columnDefinition = "varchar(512) COMMENT '用户类型'")
    private UserType userType;

}
