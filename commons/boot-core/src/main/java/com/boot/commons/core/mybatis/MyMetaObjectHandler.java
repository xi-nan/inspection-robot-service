package com.boot.commons.core.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.boot.commons.core.security.JwtUser;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


/**
 * MyMetaObjectHandler
 * 填充器
 *
 * @author XINAN
 * @date 2019/7/22
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
//        StaticLog.info("starter insert fill ....")
        Long now = System.currentTimeMillis();
        String user = "[SYS]";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication && authentication.getPrincipal() instanceof JwtUser) {
            user = this.composeUser((JwtUser) authentication.getPrincipal());
        }
        String finalUser = user;
        this.strictInsertFill(metaObject, "creator", () -> finalUser, String.class);
        this.strictInsertFill(metaObject, "createTime", () -> now, Long.class);
        this.strictInsertFill(metaObject, "modifier", () -> finalUser, String.class);
        this.strictInsertFill(metaObject, "modifyTime", () -> now, Long.class);
    }

    private String composeUser(JwtUser principal) {
        return principal.getUsername() + " (" + principal.getUserType().name() + ":" + principal.getId() + ")";
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        StaticLog.info("starter update fill ....")
        String user = "[SYS]";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication && authentication.getPrincipal() instanceof JwtUser) {
            user = this.composeUser((JwtUser) authentication.getPrincipal());
        }
        String finalUser = user;
        this.strictUpdateFill(metaObject, "modifier", () -> finalUser, String.class);
        this.strictInsertFill(metaObject, "modifyTime", System::currentTimeMillis, Long.class);
    }

}