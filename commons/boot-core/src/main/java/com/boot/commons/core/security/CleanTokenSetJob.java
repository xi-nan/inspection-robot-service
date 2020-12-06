package com.boot.commons.core.security;

import com.boot.commons.core.redis.RedisComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;


/**
 * 清理缓存用户token的set集合中已失效的数据
 */
@Component
@Slf4j
public class CleanTokenSetJob {

    @Autowired
    private RedisComponent redisComponent;

    /**
     * 每天两点执行
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void run() {
        log.debug("开始清理缓存用户token的set集合中已失效的数据");
        Set<String> keys = redisComponent.getKeys(JwtTokenUtil.USER_TOKENS_GROUP + "*");
        for (String key : keys) {
            log.debug("用户: " + key);
            for (String token : redisComponent.setGet(key)) {
                if (!redisComponent.hasKey(token)) {
                    log.debug("已失效: " + token);
                    redisComponent.del(JwtTokenUtil.TOKEN_USER_GROUP + key);
                }
            }
        }
    }

}
