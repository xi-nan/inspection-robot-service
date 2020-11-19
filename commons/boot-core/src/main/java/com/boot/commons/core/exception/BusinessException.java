package com.boot.commons.core.exception;

import cn.hutool.core.util.StrUtil;
import com.boot.commons.core.exception.enums.IErrCodeEnum;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final IErrCodeEnum code;
    private final Object data;
    private final String message;

    public BusinessException(IErrCodeEnum code, Object data) {
        this.code = code;
        this.data = data;
        this.message = code.getDesc();
    }

    public BusinessException(IErrCodeEnum code, Object data, Object... msgArgs) {
        this.code = code;
        this.data = data;
        this.message = StrUtil.format(code.getDesc(), msgArgs);
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
