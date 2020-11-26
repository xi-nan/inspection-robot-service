package com.boot.commons.core.conf;

import com.boot.commons.core.properties.SiteProperties;
import com.boot.commons.core.security.JwtProperties;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * SwaggerConfig
 *
 * @author XINAN
 * @date 2019/7/23
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
class SwaggerConfig {

    private final SiteProperties siteProperties;
    private final JwtProperties jwtProperties;

    public SwaggerConfig(JwtProperties jwtProperties, SiteProperties siteProperties) {
        this.jwtProperties = jwtProperties;
        this.siteProperties = siteProperties;
    }

    @Bean
    Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .host(siteProperties.getHost())
                .apiInfo(new ApiInfoBuilder().title(siteProperties.getExplanation() + " API 接口文档").version("1.0").build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.boot"))
                .paths(PathSelectors.any())
                .build()
                //添加登录认证
                .securitySchemes(Collections.singletonList((new ApiKey(jwtProperties.getTokenHeader(), jwtProperties.getTokenHeader(), "header"))));
//                .securityContexts(listOf(SecurityContext.builder().forPaths(PathSelectors.regex("/.*")).build()))
    }
}