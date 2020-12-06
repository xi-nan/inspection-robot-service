package com.boot.business.sysuser.model.po;

import com.boot.commons.core.model.po.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;


@Entity
@ApiModel(value = "用户角色表")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(appliesTo = "sys_user_role", comment = "管理后台用户角色表", indexes = {
        @Index(name = "IX_SysUserRole_deleted", columnNames = {"deleted"}),
        @Index(name = "IX_SysUserRole_deleted_userId", columnNames = {"deleted", "userId"}),
        @Index(name = "IX_SysUserRole_deleted_roleId", columnNames = {"deleted", "roleId"})
})
public class SysUserRole extends BasePo<SysUserRole> implements Serializable {

    @ApiModelProperty(value = "用户Id")
    @Column(columnDefinition = "bigint(20) COMMENT '用户Id'")
    private Long userId;

    @ApiModelProperty(value = "角色Id")
    @Column(columnDefinition = "bigint(20) COMMENT '角色Id'")
    private Long roleId;

}
