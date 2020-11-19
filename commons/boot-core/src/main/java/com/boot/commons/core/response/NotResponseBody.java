package com.boot.commons.core.response;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * NotResponseBody
 * 绕过数据统一响应,注解方法将不被统一响应体R包裹
 *
 * @author XINAN
 * @date 2020/7/7
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface NotResponseBody {
}
