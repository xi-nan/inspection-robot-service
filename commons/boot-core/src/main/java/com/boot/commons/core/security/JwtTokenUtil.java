package com.boot.commons.core.security;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.boot.commons.core.redis.RedisComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Duration;
import java.util.Set;


/**
 * JwtTokenUtil
 *
 * @author XINAN
 * @date 2019/8/1
 */
@Component
public class JwtTokenUtil implements Serializable {

    public static final String CONNECTOR = "::";
    public static final String TOKEN_USER_GROUP = "token_user" + CONNECTOR;
    public static final String USER_TOKENS_GROUP = "user_token" + CONNECTOR;

    @Autowired
    private RedisComponent redisComponent;

    /**
     * 根据用户信息生成token
     */
    public JwtUser generateToken(JwtUser user, Duration expiration, Boolean autoRenewal) {
        user.setExpiration(expiration);
        user.setAutoRenewal(autoRenewal);
        String token = user.getUserType() + CONNECTOR + IdUtil.fastSimpleUUID();
        user.setToken(token);
        redisComponent.set(TOKEN_USER_GROUP + token, JSONUtil.toJsonStr(user), expiration.getSeconds());
        redisComponent.setPut(USER_TOKENS_GROUP + user.getUserType() + user.getId(), token);
        return user;
    }


    /**
     * 获取token对应的JwtUser
     */
    public JwtUser getJwtUser4Token(String token) {
        Object o = redisComponent.get(TOKEN_USER_GROUP + token);
        if (null == o) {
            return null;
        }
        String userJson = String.valueOf(o);
        return JSONUtil.toBean(userJson, JwtUser.class);
    }

    /**
     * 刷新token有效期
     */
    public void refreshToken(String token, Duration expiration) {
        redisComponent.expire(TOKEN_USER_GROUP + token, expiration.getSeconds());
    }

    /**
     * 清除用户所有token
     */
    public void cleanToken4UserId(String userType, Long userId) {
        Set<String> tokens = redisComponent.setGet(USER_TOKENS_GROUP + userType + userId);
        for (String token : tokens) {
            redisComponent.del(TOKEN_USER_GROUP + token);
        }
        redisComponent.del(USER_TOKENS_GROUP + userType + userId);
    }

}