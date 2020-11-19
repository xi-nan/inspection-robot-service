package com.boot.business.sysuser.model.dto;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
public class RoleDTO implements Serializable {

    private Long id;

    @ApiModelProperty(value = "角色描述")
    private String roleDesc;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "默认角色设置")
    private Boolean defaultRole;

    @ApiModelProperty(value = "角色拥有的权限ID数组")
    private Long[] permissionIds;


    /**
     * 将其他对象转换成本对象
     */
    public static <R> RoleDTO warp(R source) {
        RoleDTO dto = new RoleDTO();
        BeanUtil.copyProperties(source, dto);
        return dto;
    }
}
