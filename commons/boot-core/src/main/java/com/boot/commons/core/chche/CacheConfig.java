package com.boot.commons.core.chche;

import com.boot.commons.core.properties.SiteProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Objects;

/**
 * CacheConfig
 *
 * @author XINAN
 * @date 2020/7/2
 */
@Configuration
@EnableConfigurationProperties(SiteProperties.class)
@EnableCaching  //开启缓存
public class CacheConfig extends CachingConfigurerSupport {

    @Autowired
    SiteProperties siteProperties;

    @Resource
    private RedisConnectionFactory factory;

    /**
     * 自定义生成redis-key
     *
     * @return
     */
    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(o.getClass().getName()).append(".");
            sb.append(method.getName()).append(".");
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            System.out.println("keyGenerator=" + sb.toString());
            return sb.toString();
        };
    }

    @Bean
    @Override
    public CacheResolver cacheResolver() {
        return new SimpleCacheResolver(Objects.requireNonNull(cacheManager()));
    }

    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        // 用于捕获从Cache中进行CRUD时的异常的回调处理器。
        return new SimpleCacheErrorHandler();
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        RedisCacheConfiguration cacheConfiguration =
                RedisCacheConfiguration.defaultCacheConfig()
                        // 默认过期时间30秒
                        .entryTtl(Duration.ofSeconds(30))
                        // key前缀设置为项目名
                        .computePrefixWith((cacheName) -> siteProperties.getAppName() + "::cache::" + cacheName + "::")
                        .disableCachingNullValues()
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        return RedisCacheManager.builder(factory).cacheDefaults(cacheConfiguration).build();

    }

}
