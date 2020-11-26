package com.boot.commons.core.conf;

import com.boot.commons.core.properties.SiteProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * BootConfig
 *
 * @author XINAN
 * @date 2019/7/22
 */
@Configuration
@EnableTransactionManagement
@EnableScheduling // 定时任务
@EnableConfigurationProperties(SiteProperties.class)
@MapperScan("com.boot.**.mapper")
@EntityScan({"com.boot.**.entity", "com.boot.**.po"})
public class BootConfig {

}