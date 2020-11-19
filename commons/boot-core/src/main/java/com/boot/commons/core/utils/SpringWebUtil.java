package com.boot.commons.core.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * SpringWebUtil
 *
 * @author XINAN
 * @date 2019/7/30
 */
public class SpringWebUtil {

    /**
     * 获取客户端真实ip
     *
     * @return
     */
    public static String getRemoteIP(HttpServletRequest request) {
        String ip = request.getHeader("X-nginx-real-ip");
        String unknown = "unknown";

        if (StringUtils.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (StringUtils.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_REAL_IP");
        }
        if (StringUtils.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StringUtils.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            return "";
        }
        return ip;
    }

}
