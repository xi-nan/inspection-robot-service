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
            user = ((JwtUser) authentication.getPrincipal()).getTypeAndId();
        }
        this.setInsertFieldValByName("creator", user, metaObject);
        this.setInsertFieldValByName("createTime", now, metaObject);
        this.setInsertFieldValByName("modifier", user, metaObject);
        this.setInsertFieldValByName("modifyTime", now, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        StaticLog.info("starter update fill ....")
        String user = "[SYS]";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication && authentication.getPrincipal() instanceof JwtUser) {
            user = ((JwtUser) authentication.getPrincipal()).getTypeAndId();
        }
        this.setUpdateFieldValByName("modifier", user, metaObject);
        this.setUpdateFieldValByName("modifyTime", System.currentTimeMillis(), metaObject);
    }

}