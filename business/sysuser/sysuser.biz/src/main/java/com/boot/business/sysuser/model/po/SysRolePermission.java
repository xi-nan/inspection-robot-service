package com.boot.business.sysuser.model.po;

import com.boot.commons.core.model.po.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@ApiModel(value = "角色权限表")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table(appliesTo = "sys_role_permission", comment = "管理后台角色权限表", indexes = {
        @Index(name = "IX_SysRolePermission_deleted", columnNames = {"deleted"}),
        @Index(name = "IX_SysRolePermission_deleted_permissionId", columnNames = {"deleted", "permissionId"}),
        @Index(name = "IX_SysRolePermission_deleted_roleId", columnNames = {"deleted", "roleId"})
})
public class SysRolePermission extends BasePo<SysRolePermission> implements Serializable {

    @ApiModelProperty(value = "权限Id")
    @Column(columnDefinition = "bigint(20) COMMENT '权限Id'")
    private Long permissionId;

    @ApiModelProperty(value = "角色Id")
    @Column(columnDefinition = "bigint(20) COMMENT '角色Id'")
    private Long roleId;


}
