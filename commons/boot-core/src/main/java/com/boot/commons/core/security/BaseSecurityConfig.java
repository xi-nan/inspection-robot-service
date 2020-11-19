package com.boot.commons.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * BaseSecurityConfig
 *
 * @author XINAN
 * @date 2019/8/1
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class BaseSecurityConfig extends SuperSecurityConfig {

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        super.defaultConfigure(httpSecurity);
        httpSecurity.authorizeRequests()
                //跨域请求会先进行一次options请求
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                // swagger
                .antMatchers("/doc.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/*/api-docs").permitAll()
                .antMatchers("/*/api-docs-ext").permitAll()
                .antMatchers("/actuator").permitAll()
                .antMatchers("/actuator/*").permitAll()
                // 验证码
                .antMatchers("/base/captcha/**").permitAll()
                // 省市区
                .antMatchers("/base/region/**").permitAll()
                // 所有请求都需要认证
                .anyRequest().authenticated();
        setStrategyName();
    }

    @Bean
    public void setStrategyName() {
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}