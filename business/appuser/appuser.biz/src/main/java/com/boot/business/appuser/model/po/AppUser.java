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
        @Index(name = "IX_AppUser_username", columnNames = {"username"})
})
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AppUser extends BasePo<AppUser> implements Serializable {

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