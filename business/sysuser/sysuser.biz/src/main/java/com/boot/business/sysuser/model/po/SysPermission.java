package com.boot.business.sysuser.model.po;

import com.boot.commons.core.model.po.BasePo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(appliesTo = "sys_permission", comment = "管理后台权限项表", indexes = {
        @Index(name = "IX_SysPermission_deleted", columnNames = {"deleted"}),
        @Index(name = "IX_SysPermission_parentId", columnNames = "parentId")
})
public class SysPermission extends BasePo<SysPermission> implements Serializable {

    @ApiModelProperty(value = "父级ID", example = "0")
    @Column(columnDefinition = "bigint(20) COMMENT '父级ID'")
    private Long parentId;

    @ApiModelProperty(value = "父级菜单级别", example = "0")
    @Column(columnDefinition = "bigint(1) COMMENT '父级菜单级别'")
    private Integer parentLevel;

    @ApiModelProperty(value = "菜单类型")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '菜单类型'")
    private String type;

    @ApiModelProperty(value = "菜单名称")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '菜单名称'")
    private String name;

    @ApiModelProperty(value = "资源码")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '资源码'")
    private String code;

    @ApiModelProperty(value = "URL")
    @Column(columnDefinition = "VARCHAR(512) COMMENT 'URL'")
    private String url;

    @ApiModelProperty(value = "排序参数")
    @Column(columnDefinition = "bigint(1) COMMENT '排序参数'")
    private Integer sortOrder = 0;

    @ApiModelProperty(value = "菜单级别")
    @Column(columnDefinition = "bigint(1) COMMENT '菜单级别'")
    private Integer level = 0;

}
