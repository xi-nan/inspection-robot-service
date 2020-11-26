package com.boot.business.appuser.conf;

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
 * AppUserSecurityConfig
 *
 * @author XINAN
 * @date 2019/8/1
 */

@Configuration
@Order(2)
public class AppUserSecurityConfig extends SuperSecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Lazy
    @Autowired
    private AppUserJwtFilter jwtFilter;

    @Lazy
    @Autowired
    @Qualifier("appUserServiceImpl")
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        super.defaultConfigure(httpSecurity.antMatcher(AppUserJwtFilter.ANT_PATTERN));
        httpSecurity.authorizeRequests()
                .antMatchers("/app/user/checkMobile").permitAll()
                .antMatchers("/app/user/sendRegisterCode").permitAll()
                .antMatchers("/app/user/register").permitAll()
                .antMatchers("/app/user/login").permitAll()
                .antMatchers("/app/user/sendResetPwdCode").permitAll()
                .antMatchers("/app/user/resetPwd").permitAll()
                .antMatchers("/app/user/wp/auth").permitAll()
                .antMatchers("/app/user/wp/jsapiSignature").permitAll()
                // 绑定手机发送验证码
                .antMatchers("/app/sms/sendSmsCode/bindMobile").permitAll()
                // 验证码校验手机号进行绑定
                .antMatchers("/app/user/code/bindMobile").permitAll()
                // 用户协议
                .antMatchers("/app/userAgreement/*").permitAll()
                // 所有请求都需要登陆认证 一定要放在放行配置之后 否则会被拦截
                .anyRequest().hasRole(UserType.APP.name());
        // 所有请求全部放行 使用注解控制
        // .anyRequest().permitAll();
        // 添加JWT filter
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AuthenticationManager appAuthenticationManager() {
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