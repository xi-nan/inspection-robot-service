package com.boot.commons.core.conf;

import com.boot.commons.core.properties.SiteProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * AsyncConfig
 * 异步方法线程池
 *
 * @author xinan
 * @date 2020/11/26
 */
@Configuration
@EnableAsync // 启用springboot异步注解 @Async
public class AsyncConfig implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //设置核心线程数
        executor.setCorePoolSize(2);
        //设置最大线程数
        executor.setMaxPoolSize(5);
        //线程池所使用的缓冲队列
        executor.setQueueCapacity(Integer.MAX_VALUE);
        executor.setKeepAliveSeconds(30);
        executor.setThreadNamePrefix(SiteProperties._this.getAppName() + "_");
        executor.initialize();
        return executor;
    }
}
