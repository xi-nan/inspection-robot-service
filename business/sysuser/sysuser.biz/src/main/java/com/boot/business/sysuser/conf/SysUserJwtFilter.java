package com.boot.business.sysuser.conf;

import cn.hutool.log.StaticLog;
import com.boot.commons.core.security.JwtProperties;
import com.boot.commons.core.security.JwtTokenUtil;
import com.boot.commons.core.security.JwtUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AdminJwtFilter
 *
 * @author XINAN
 * @date 2019/8/2
 */
@Component
public class SysUserJwtFilter extends OncePerRequestFilter {

    public static final String ANT_PATTERN = "/sys/**";
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        do {
            if (!new AntPathMatcher().match(ANT_PATTERN, request.getServletPath())) {
                break;
            }
            String requestHeader = request.getHeader(jwtProperties.getTokenHeader());
            if (StringUtils.isBlank(requestHeader)) {
                break;
            }
            String authToken = requestHeader;
//            if (requestHeader.startsWith("Bearer ")) {
//                // The part after "Bearer "
//                authToken = requestHeader.substring(7);
//            }
            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                break;
            }
            // It is not compelling necessary to load the use details from the database. You could also store the information
            // in the token and read it from it. It's up to you ;)
            try {
                JwtUser user = jwtTokenUtil.getJwtUser4Token(authToken);
                // For simple validation it is completely sufficient to just check the token integrity. You don't have to call
                // the database compellingly. Again it's up to you ;)
                if (null != user) {
                    if (user.getAutoRenewal()) {
                        jwtTokenUtil.refreshToken(authToken, user.getExpiration());
                    }
                    AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (UsernameNotFoundException e) {
                StaticLog.debug("token对应账号已被清除,username:[${e.message}]");
            }
        }
        while (false);
        chain.doFilter(request, response);
    }

}