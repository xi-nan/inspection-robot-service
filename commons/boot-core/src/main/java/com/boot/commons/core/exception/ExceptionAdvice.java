package com.boot.commons.core.exception;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.log.StaticLog;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.boot.commons.core.exception.enums.ErrCodeEnum;
import com.boot.commons.core.model.R;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ExceptionAdvice
 *
 * @author XINAN
 * @date 2019/7/22
 */

@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * SQLException SQL执行异常处理
     */
    @ExceptionHandler(SQLException.class)
    R<?> handleSQLException(SQLException e) {
        StaticLog.error(e);
        return this.handler(ErrCodeEnum.E_10010.wrapThis(e.getMessage()));
    }

    private R<?> handler(BusinessException e) {
        return R.fail(e.getData(), e.getCode(), e.getMessage());
    }

    /**
     * PersistenceException 持久化异常处理(MyBatis异常)
     */
    @ExceptionHandler(PersistenceException.class)
    R<?> handlePersistenceException(PersistenceException e) {
        StaticLog.error(e);
        return this.handler(ErrCodeEnum.E_10011.wrapThis(e.getMessage()));
    }

    /**
     * MybatisPlus异常处理
     */
    @ExceptionHandler(MybatisPlusException.class)
    R<?> handleMybatisPlusException(Exception e) {
        StaticLog.error(e);
        return this.handler(ErrCodeEnum.E_10012.wrapThis(e.getMessage()));
    }

    /**
     * HttpMediaTypeNotSupportedException 请求异常处理
     */
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    R<?> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        MediaType mediaType = e.getContentType();
        return this.handler(ErrCodeEnum.E_20001.wrapThis(ObjectUtil.isNull(mediaType) ? "" : mediaType.getType()));
    }

    /**
     * HttpMessageNotReadableException 请求异常处理
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    R<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return this.handler(ErrCodeEnum.E_20002.wrapThis());
    }

    /**
     * MissingServletRequestParameterException 请求异常处理
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    R<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return this.handler(ErrCodeEnum.E_20003.wrapThis(e.getParameterName(), e.getParameterType()));
    }

    /**
     * MethodArgumentNotValidException 参数校验异常处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    R<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        //按需重新封装需要返回的错误信息
        List<Map<String, Object>> invalidArguments = new ArrayList<>();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            invalidArguments.add(new HashMap<String, Object>(3) {{
                put("msg", error.getDefaultMessage());
                put("field", error.getField());
                put("rejectedValue", error.getRejectedValue());
            }});
        }
        return this.handler(new BusinessException(ErrCodeEnum.E_20004, invalidArguments));
    }

    /**
     * HttpRequestMethodNotSupportedException 请求异常处理
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    R<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return this.handler(ErrCodeEnum.E_20005.wrapThis(e.getMethod()));
    }

    /**
     * AccessDeniedException 异常处理
     */
    @ExceptionHandler(AccessDeniedException.class)
    R<?> handle(AccessDeniedException e) {
        // 重新抛出 由SpringSecurity处理
        throw e;
    }

    /**
     * BadCredentialsException 账号密码校验异常处理
     */
    @ExceptionHandler(BadCredentialsException.class)
    R<?> handleBadCredentialsException(BadCredentialsException e) {
        // StaticLog.error(e)
        return this.handler(ErrCodeEnum.E_20013.wrapThis());
    }

    /**
     * UsernameNotFoundException 用户不存在异常处理
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    R<?> handleUsernameNotFoundException(UsernameNotFoundException e) {
        // StaticLog.error(e)
        return this.handler(ErrCodeEnum.E_20012.wrapThis(String.format("未找到用户名为[%s]的账号", e.getMessage())));
    }

    /**
     * InternalAuthenticationServiceException 自定义认证异常处理
     */
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    R<?> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
        // StaticLog.error(e)
        // 重新抛出 由SpringSecurity处理
        throw e;
    }

    /**
     * BusinessException 业务异常处理
     */
    @ExceptionHandler(BusinessException.class)
    R<?> handleBusinessException(BusinessException e) {
        return this.handler(e);
    }

    /**
     * 所有异常统一处理
     */
    @ExceptionHandler(Exception.class)
    R<?> handleException(Exception e) {
        StaticLog.error(e);
        return this.handler(ErrCodeEnum.E_10001.wrapThis(e.getMessage()));
    }


}