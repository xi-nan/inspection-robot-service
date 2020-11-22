package com.boot.commons.core.conf;

import com.boot.commons.core.properties.SiteProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.Executor;

/**
 * BootConfig
 *
 * @author XINAN
 * @date 2019/7/22
 */
@Configuration
@EnableAsync // 启用springboot异步注解 @Async
@EnableTransactionManagement
@EnableScheduling // 定时任务
@EnableConfigurationProperties(SiteProperties.class)
@MapperScan("com.boot.**.mapper")
@EntityScan({"com.boot.**.entity", "com.boot.**.po"})
public class BootConfig {

    /**
     * 异步方法线程池
     */
    @Bean
    Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //设置核心线程数
        executor.setCorePoolSize(10);
        //设置最大线程数
        executor.setMaxPoolSize(Integer.MAX_VALUE);
        //线程池所使用的缓冲队列
        executor.setQueueCapacity(Integer.MAX_VALUE);
        executor.setKeepAliveSeconds(30);
        executor.setThreadNamePrefix(SiteProperties._this.getAppName() + "_");
        executor.initialize();
        return executor;
    }
}