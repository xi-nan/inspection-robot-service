package com.boot.business.sysuser.model.po;

import com.boot.commons.core.model.po.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * SysUser 管理员(用户)
 *
 * @author XINAN
 * @date 2019/7/19
 */
@Entity
@Table(appliesTo = "sys_user", comment = "管理员用户表", indexes = {
        @Index(name = "IX_SysUser_deleted", columnNames = {"deleted"}),
        @Index(name = "IX_SysUser_deleted_username", columnNames = {"deleted", "username"})
})
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser extends BasePo<SysUser> implements Serializable {

    @Column(columnDefinition = "VARCHAR(512) COMMENT '登录名'")
    private String username;

    @Column(columnDefinition = "VARCHAR(512) COMMENT '密码'")
    private String password;

    @Column(columnDefinition = "bit(1) COMMENT '是否启用 0:false 1:true'")
    private Boolean enabled = true;

    @Column(columnDefinition = "VARCHAR(512) COMMENT '昵称'")
    private String nickName;

    @Column(columnDefinition = "VARCHAR(512) COMMENT '手机'")
    private String mobile;
}