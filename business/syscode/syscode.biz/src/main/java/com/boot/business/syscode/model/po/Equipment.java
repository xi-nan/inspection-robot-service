package com.boot.business.syscode.model.po;

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
@Table(appliesTo = "equipment", comment = "设备")
public class Equipment extends BasePo<Equipment> {

//    @ApiModelProperty(value = "设备code")
//    @Column(columnDefinition = "varchar(255) COMMENT '设备code,不可重复'")
//    private String code;

    @ApiModelProperty(value = "关联账号")
    @Column(columnDefinition = "BIGINT(20) NOT NULL COMMENT '关联账号'")
    private Long userId;

    @ApiModelProperty(value = "PLC IP")
    @Column(columnDefinition = "text COMMENT 'PLC IP'")
    private String plcIp;

    @ApiModelProperty(value = "PLC端口")
    @Column(columnDefinition = "text COMMENT 'PLC端口'")
    private String plcPort;

    @ApiModelProperty(value = "可见光摄像头 IP")
    @Column(columnDefinition = "text COMMENT '可见光摄像头 IP'")
    private String vcIp;

    @ApiModelProperty(value = "可见光摄像头 端口")
    @Column(columnDefinition = "text COMMENT '可见光摄像头 端口'")
    private String vcPort;

    @ApiModelProperty(value = "可见光摄像头 密码")
    @Column(columnDefinition = "text COMMENT '可见光摄像头 密码'")
    private String vcAccount;

    @ApiModelProperty(value = "可见光摄像头 密码")
    @Column(columnDefinition = "text COMMENT '可见光摄像头 密码'")
    private String vcPwd;

    @ApiModelProperty(value = "热成像摄像头 IP")
    @Column(columnDefinition = "text COMMENT '热成像摄像头 IP'")
    private String tcIp;

    @ApiModelProperty(value = "热成像摄像头 端口")
    @Column(columnDefinition = "text COMMENT '热成像摄像头 端口'")
    private String tcPort;

    @ApiModelProperty(value = "热成像摄像头 密码")
    @Column(columnDefinition = "text COMMENT '热成像摄像头 密码'")
    private String tcAccount;

    @ApiModelProperty(value = "可见光摄像头 密码")
    @Column(columnDefinition = "text COMMENT '热成像摄像头 密码'")
    private String tcPwd;

    @ApiModelProperty(value = "功能描述")
    @Column(columnDefinition = "text COMMENT '功能描述'")
    private String funcDescr;

    @ApiModelProperty(value = "范围描述")
    @Column(columnDefinition = "text COMMENT '范围描述'")
    private String scopeDescr;

    @ApiModelProperty(value = "是否启用")
    @Column(columnDefinition = "bit(1) COMMENT '是否启用 0:false 1:true'")
    private Boolean enabled = true;

}
