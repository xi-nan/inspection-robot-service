package com.boot.business.appuser.model.po;

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
 * AppUser 用户
 *
 * @author XINAN
 * @date 2019/7/19
 */
@Entity
@Table(appliesTo = "app_user", comment = "APP用户表", indexes = {
        @Index(name = "IX_AppUser_deleted", columnNames = {"deleted"}),
        @Index(name = "IX_AppUser_deleted_username", columnNames = {"deleted", "username"})
})
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AppUser extends BasePo<AppUser> implements Serializable {

    @Column(columnDefinition = "VARCHAR(512) COMMENT '登录名'")
    private String username;

    @Column(columnDefinition = "VARCHAR(512) COMMENT '密码'")
    private String password;

    @Column(columnDefinition = "VARCHAR(512) COMMENT '昵称'")
    private String nickName;

    @Column(columnDefinition = "VARCHAR(512) COMMENT '手机'")
    private String mobile;

}