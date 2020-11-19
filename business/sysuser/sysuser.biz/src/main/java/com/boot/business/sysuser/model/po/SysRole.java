package com.boot.business.sysuser.model.po;

import com.boot.commons.core.model.po.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@ApiModel(value = "角色表")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(appliesTo = "sys_role", comment = "管理后台角色表")
public class SysRole extends BasePo<SysRole> implements Serializable {

    @ApiModelProperty(value = "角色描述")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '角色描述'")
    private String roleDesc;

    @ApiModelProperty(value = "角色名称")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '角色名称'")
    private String roleName;

    @ApiModelProperty(value = "默认角色设置")
    @Column(columnDefinition = "bit(1) COMMENT '默认角色设置'")
    private Boolean defaultRole;

}
