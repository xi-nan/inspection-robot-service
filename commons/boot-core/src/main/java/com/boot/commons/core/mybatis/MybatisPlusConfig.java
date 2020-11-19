package com.boot.commons.core.mybatis;

import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Collections;


/**
 * MybatisPlusConfig
 *
 * @author XINAN
 * @date 2019/7/22
 */

@Configuration
public class MybatisPlusConfig {

    /**
     * 1.分页插件
     * 2.击 SQL 阻断解析器
     */
    @Bean
    PaginationInterceptor paginationInterceptor() {
        // 分页插件
        val paginationInterceptor = new PaginationInterceptor();
        // 攻击 SQL 阻断解析器、加入解析链
        paginationInterceptor.setSqlParserList(Collections.singletonList(new BlockAttackSqlParser()));
        return paginationInterceptor;
    }

    /**
     * 性能分析拦截器，不建议生产使用
     * 用来观察 SQL 执行情况及执行时长
     */
    @Bean
    @Profile({"dev", "test"})
    // 设置 dev test 环境开启
    PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

}