package com.boot.business.syscode.model.param;

import com.boot.commons.core.model.param.ValidGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EquipmentSaveParam {

    @NotBlank(message = "修改时ID不能为空！", groups = {ValidGroup.upd.class})
    @ApiModelProperty(value = "设备ID")
    private Long id;

    @ApiModelProperty(value = "关联账号")
    @NotNull(message = "关联账号不能为空！")
    private Long userId;

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

}

