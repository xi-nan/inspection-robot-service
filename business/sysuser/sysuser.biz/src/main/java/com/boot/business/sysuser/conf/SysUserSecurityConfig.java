package com.boot.business.sysuser.conf;

import com.boot.commons.core.security.SuperSecurityConfig;
import com.boot.commons.core.security.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;


/**
 * AdminSecurityConfig
 *
 * @author XINAN
 * @date 2019/8/1
 */

@Configuration
@Order(1)
public class SysUserSecurityConfig extends SuperSecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Lazy
    @Autowired
    private SysUserJwtFilter jwtFilter;

    @Lazy
    @Autowired
    @Qualifier("sysUserServiceImpl")
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        super.defaultConfigure(httpSecurity.antMatcher(SysUserJwtFilter.ANT_PATTERN));
        httpSecurity.authorizeRequests()
                .antMatchers("/sys/admin/login").permitAll()
//                .antMatchers("/sys/admin/add").permitAll()
                // 所有请求都需要登陆认证 一定要放在放行配置之后 否则会被拦截
                .anyRequest().hasRole(UserType.ADMIN.name());
        // 所有请求全部放行 使用注解控制
        // .anyRequest().permitAll();
        // 添加JWT filter
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AuthenticationManager adminAuthenticationManager() {
        ProviderManager providerManager = new ProviderManager(Collections.singletonList(super.authenticationProvider(userDetailsService, passwordEncoder)));
        //不擦除认证密码，擦除会导致TokenBasedRememberMeServices因为找不到Credentials再调用UserDetailsService而抛出UsernameNotFoundException
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return providerManager;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(super.authenticationProvider(userDetailsService, passwordEncoder));
    }

}
