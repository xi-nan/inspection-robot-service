package com.boot.commons.core.conf;

import com.boot.commons.core.properties.SiteProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.util.Collections;
import java.util.List;


/**
 * WebApplicationConfig
 *
 * @author XINAN
 * @date 2019/7/23
 */
@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, mappingJackson2HttpMessageConverters());
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverters() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // 忽略空值字段
        converter.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 设置日期格式
        // converter.objectMapper.dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        // 设置中文编码格式
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));

        return converter;
    }

    /**
     * 跨域过滤器
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许任何的head头部
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        // 允许任何域名使用
        corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
        // 允许任何的请求方法
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    /**
     * 文件上传临时路径
     */
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String tmpPath = SiteProperties._this.getFileUploadDir() + "/tmp";
        // 自动创建目录
        new File(tmpPath).mkdirs();
        factory.setLocation(tmpPath);
        return factory.createMultipartConfig();
    }
}