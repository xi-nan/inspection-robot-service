package com.boot.commons.core.security;

import com.boot.commons.core.exception.BusinessException;
import com.boot.commons.core.exception.enums.ErrCodeEnum;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author XINAN
 */

public class LoginUserUtil {

    public static Long getLoginUserId() {
        return getLoginUser().getId();
    }

    public static JwtUser getLoginUser() {
        return getLoginUser(true);
    }

    public static JwtUser getLoginUser(Boolean throwEx) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof JwtUser) {
            return (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        if (!throwEx) {
            return null;
        }
        // 可匿名访问接口内获取用户信息 throwEx应为true 且业务内需判断用户信息是否为null
        throw new BusinessException(ErrCodeEnum.E_10001, null, "获取当前登陆用户信息失败.");
    }

    public static Long getLoginUserId(Boolean throwEx) {
        JwtUser loginUser = getLoginUser(throwEx);
        return null == loginUser ? null : loginUser.getId();
    }

}
