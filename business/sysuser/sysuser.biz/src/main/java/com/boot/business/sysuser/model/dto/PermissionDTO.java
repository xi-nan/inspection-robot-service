package com.boot.business.sysuser.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class PermissionDTO implements Serializable {

    private Long id;

    @ApiModelProperty(value = "父级ID", example = "0")
    private Long parentId;

    @ApiModelProperty(value = "菜单类型:0:页面，1：按钮", example = "0")
    private Integer type;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "资源码")
    private String code;

    @ApiModelProperty(value = "URL")
    private String url;

    @ApiModelProperty(value = "排序参数：支持小数，越小越靠前")
    private Integer sortOrder;

    @ApiModelProperty(value = "菜单级别：eg:系统权限菜单为0级菜单", example = "0")
    private Integer level;

    @ApiModelProperty(value = "子权限项")
    private List<PermissionDTO> children;

    public static List<PermissionDTO> makeTree(List<PermissionDTO> list) {
        List<PermissionDTO> parent = new ArrayList<>();
        // get parentId = null;
        for (PermissionDTO e : list) {
            if (e.getParentId() == null || e.getParentId().equals(0L)) {
                e.setChildren(new ArrayList<>(0));
                parent.add(e);
            }
        }
        // 删除parentId = null;
        list.removeAll(parent);

        makeChildren(parent, list);

        return parent;
    }

    private static void makeChildren(List<PermissionDTO> parent, List<PermissionDTO> children) {
        if (children.isEmpty()) {
            return;
        }
        for (PermissionDTO c1 : parent) {
            List<PermissionDTO> tmp = new ArrayList<>();
            for (PermissionDTO c2 : children) {
                c2.setChildren(new ArrayList<>(0));
                if (c1.getId().equals(c2.getParentId())) {
                    c1.getChildren().add(c2);
                    tmp.add(c2);
                }
            }
            if (tmp.isEmpty()) {
                continue;
            }
            children.removeAll(tmp);
            makeChildren(tmp, children);
        }
    }

}
