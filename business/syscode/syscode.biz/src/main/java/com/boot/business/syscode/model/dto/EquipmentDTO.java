package com.boot.business.syscode.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDTO {

    @ApiModelProperty(value = "设备ID")
    private Long id;

    @ApiModelProperty(value = "关联账号")
    private Long userId;

    @ApiModelProperty(value = "关联账号")
    private String username;

    @ApiModelProperty(value = "PLC IP")
    private String plcIp;

    @ApiModelProperty(value = "PLC端口")
    private String plcPort;

    @ApiModelProperty(value = "可见光摄像头 IP")
    private String vcIp;

    @ApiModelProperty(value = "可见光摄像头 端口")
    private String vcPort;

    @ApiModelProperty(value = "可见光摄像头 密码")
    private String vcAccount;

    @ApiModelProperty(value = "可见光摄像头 密码")
    private String vcPwd;

    @ApiModelProperty(value = "热成像摄像头 IP")
    private String tcIp;

    @ApiModelProperty(value = "热成像摄像头 端口")
    private String tcPort;

    @ApiModelProperty(value = "热成像摄像头 密码")
    private String tcAccount;

    @ApiModelProperty(value = "可见光摄像头 密码")
    private String tcPwd;

    @ApiModelProperty(value = "功能描述")
    private String funcDescr;

    @ApiModelProperty(value = "范围描述")
    private String scopeDescr;

    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;

}

