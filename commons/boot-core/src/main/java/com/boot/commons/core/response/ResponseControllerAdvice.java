package com.boot.commons.core.response;

import com.boot.commons.core.model.R;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * ResponseControllerAdvice
 *
 * @author XINAN
 * @date 2020/7/7
 */
@RestControllerAdvice(annotations = RestController.class)
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果接口返回的类型本身就是R那就没有必要进行额外的操作，返回false
        // 如果方法上加了我们的自定义注解也没有必要进行额外的操作
        return !(returnType.hasMethodAnnotation(NotResponseBody.class) || returnType.getParameterType().equals(R.class));
    }

    @Override
    public R<Object> beforeBodyWrite(Object o,
                                     MethodParameter methodParameter,
                                     MediaType mediaType,
                                     Class<? extends HttpMessageConverter<?>> aClass,
                                     ServerHttpRequest serverHttpRequest,
                                     ServerHttpResponse serverHttpResponse) {
        return R.ok(o);
    }
}
