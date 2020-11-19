package com.boot.business.sysuser.model.param;

import com.boot.commons.core.model.param.ValidGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PermissionSaveParam {

    @ApiModelProperty(value = "权限ID")
    @NotNull(message = "权限 permissionId 不可能为空！", groups = {ValidGroup.upd.class})
    private Long id;

    @ApiModelProperty(value = "父级ID", example = "0")
    @NotNull(message = "父级ID parentId 不可能为空！", groups = {ValidGroup.add.class})
    private Long parentId;

    @ApiModelProperty(value = "父级菜单级别", example = "0")
    @NotNull(message = "父级菜单级别 parentLevel 不可能为空！", groups = {ValidGroup.add.class})
    private Integer parentLevel;

    @ApiModelProperty(value = "菜单类型:0:页面，1：按钮", example = "0")
    @NotNull(message = "菜单类型 type 不可能为空！", groups = {ValidGroup.add.class, ValidGroup.upd.class})
    private Integer type;

    @ApiModelProperty(value = "菜单名称")
    @NotBlank(message = "菜单类型 name 不可能为空！", groups = {ValidGroup.add.class, ValidGroup.upd.class})
    private String name;

    @ApiModelProperty(value = "资源码")
    @NotBlank(message = "菜单类型 code 不可能为空！", groups = {ValidGroup.add.class, ValidGroup.upd.class})
    private String code;

    @ApiModelProperty(value = "URL")
    private String url;

    @ApiModelProperty(value = "排序参数：支持小数，越小越靠前")
    private BigDecimal sortOrder;

    @ApiModelProperty(value = "菜单级别：eg:系统权限菜单为0级菜单", example = "0")
//    @NotBlank(message = "菜单类型 level 不可能为空！", groups = {ValidGroup.update.class})
    private Integer level;

}
