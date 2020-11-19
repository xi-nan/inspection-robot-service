package com.boot.commons.core.security;

import cn.hutool.json.JSONUtil;
import com.boot.commons.core.exception.BusinessException;
import com.boot.commons.core.exception.enums.ErrCodeEnum;
import com.boot.commons.core.exception.enums.IErrCodeEnum;
import com.boot.commons.core.model.R;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * SuperSecurityConfig
 *
 * @author XINAN
 * @date 2019/8/1
 */

public class SuperSecurityConfig extends WebSecurityConfigurerAdapter {

    public HttpSecurity defaultConfigure(HttpSecurity httpSecurity) throws Exception {
        // 开启跨域
        httpSecurity.cors();
        // 禁用 CSRF
        httpSecurity.csrf().disable();
        // 不创建会话
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 过滤请求
        httpSecurity.authorizeRequests()
                // 公开数据
                .antMatchers("/**/public").permitAll()
                // 支付回调
                .antMatchers("/**/pay/notify").permitAll();
        // permitAll() 无限制访问。若携带的token有效，则可以在业务内取得token对应的用户信息
        // anonymous() token无效(匿名用户)才可以访问
        // 禁用缓存
        httpSecurity.headers().cacheControl();
        // 防止iframe 造成跨域
        httpSecurity.headers().frameOptions().disable();
        //添加自定义未授权和未登录结果返回
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(restAccessDeniedHandler())
                .authenticationEntryPoint(restAuthenticationEntryPoint());
        return httpSecurity;
    }

    private AccessDeniedHandler restAccessDeniedHandler() {
        return (request, response, exception) -> {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            Throwable cause = exception.getCause();
            IErrCodeEnum errCode;
            if (cause instanceof BusinessException) {
                errCode = ((BusinessException) cause).getCode();
            } else {
                response.setStatus(401);
                errCode = ErrCodeEnum.E_20010;
            }
            response.getWriter().println(JSONUtil.parse(R.fail(errCode)));
            response.getWriter().flush();
        };
    }

    private AuthenticationEntryPoint restAuthenticationEntryPoint() {
        return (request, response, exception) -> {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            Throwable cause = exception.getCause();
            IErrCodeEnum errCode;
            if (cause instanceof BusinessException) {
                errCode = ((BusinessException) cause).getCode();
            } else {
                response.setStatus(401);
                errCode = ErrCodeEnum.E_20011;
            }
            response.getWriter().println(JSONUtil.parse(R.fail(errCode)));
            response.getWriter().flush();
        };
    }


    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //允许抛出UserNotFoundException用户不存在异常
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
