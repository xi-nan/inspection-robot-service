package com.boot.business.sysuser.model.param;

import com.boot.commons.core.model.param.ValidGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * AdminSaveParam
 *
 * @author XINAN
 * @date 2019/7/22
 */
@Data
public class SysUserSaveParam {

    @ApiModelProperty(value = "ID")
    @NotNull(message = "id 不能为空！", groups = {ValidGroup.upd.class})
    @Null(message = "新增数据 id 应为空！", groups = {ValidGroup.add.class})
    private Long id;

    @ApiModelProperty(value = "登录名", required = true)
    @NotBlank(message = "登录名不能为空", groups = {ValidGroup.add.class, ValidGroup.upd.class})
    // @Pattern(regexp = PatternConstants.LOGIN_NAME, message = "登录名必须字母开头,允许5-18字节,允许字母数字下划线")
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空", groups = {ValidGroup.add.class})
    @Null(message = "修改数据 密码 应为空！", groups = {ValidGroup.upd.class})
    // @Pattern(regexp = PatternConstants.STRONG_PASSWORD_2, message = "密码必须包含字母+数字+特殊字符的组合，可用特殊字符 _!@#$%^&* ，长度8位以上")
    private String password;

    @ApiModelProperty(value = "是否启用", required = true)
    private Boolean enabled = true;

    @ApiModelProperty(value = "角色ID", required = true)
    private Long roleId;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "手机号")
    private String mobile;


}