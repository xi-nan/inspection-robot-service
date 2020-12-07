package com.boot.commons.core.redis;

import org.redisson.config.Config;
import org.redisson.spring.starter.RedissonAutoConfigurationCustomizer;
import org.springframework.stereotype.Component;

/**
 * RedissonConfig
 *
 * @author xinan
 * @date 2020/12/7
 */
@Component
public class RedissonConfig implements RedissonAutoConfigurationCustomizer {
    @Override
    public void customize(Config config) {
        config.setCodec(new org.redisson.codec.JsonJacksonCodec());
    }
}
