package com.boot.business.sysuser.model.param;


import com.boot.commons.core.model.param.ValidGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class RoleSaveParam {

    @NotNull(message = "Id 不能为空！", groups = {ValidGroup.upd.class})
    @ApiModelProperty(value = "角色ID！", example = "1")
    private Long id;

    @NotBlank(message = "角色名称 不能为空！", groups = {ValidGroup.add.class, ValidGroup.upd.class})
    @ApiModelProperty("角色名称！")
    private String roleName;

    @ApiModelProperty("角色描述！")
    private String roleDesc;

    //    @NotEmpty
    @Size(min = 1, message = "至少选择一个权限！", groups = {ValidGroup.add.class, ValidGroup.upd.class})
    @ApiModelProperty("角色权限集合！")
    private List<Long> permissionList;

}
