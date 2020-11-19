package com.boot.commons.core.model;

import com.boot.commons.core.exception.enums.IErrCodeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class R<T> implements Serializable {

    private static final long serialVersionUID = 2511341852490613670L;

    @ApiModelProperty(value = "data数据")
    private T data;

    @ApiModelProperty(value = "提示信息")
    private String msg;

    @ApiModelProperty(value = "错误码，正常为10000")
    private Integer code = 10000;

    public R() {
    }

    public R(String msg) {
        this(null, msg, 10000);
    }

    public R(T data, String msg, Integer code) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public R(T data, String msg) {
        this(data, msg, 10000);
    }

    public R(String msg, Integer code) {
        this(null, msg, code);
    }

//    public static <T> R<T> r(T data) {
//        return r(data, ErrCodeEnum.E_10001);
//    }
//
//    public static <T> R<T> r(T data, IErrCodeEnum errCode) {
//        return r(data, errCode, errCode.getDesc());
//    }
//
//    public static <T> R<T> r(T data, String errMsg) {
//        return r(data, ErrCodeEnum.E_10001, errMsg);
//    }
//
//    public static <T> R<T> r(T data, IErrCodeEnum errCode, String errMsg) {
//        // data == null || data == false
//        if ((data == null) || (data instanceof Boolean && !(Boolean) data)) {
//            return R.fail(data, errCode, errMsg);
//        }
//        return R.ok(data);
//    }

    public static <T> R<T> ok() {
        return new R<>(null, "success");
    }

    public static <T> R<T> ok(T data) {
        return new R<>(data, "success");
    }

//    public static <T> R<T> fail(T data, String errMsg) {
//        return new R<>(data, errMsg);
//    }

    public static <T> R<T> fail(T data, IErrCodeEnum errCode) {
        return new R<>(data, errCode.getDesc(), errCode.getCode());
    }

    public static <T> R<T> fail(IErrCodeEnum errCode) {
        return new R<>(null, errCode.getDesc(), errCode.getCode());
    }

    public static <T> R<T> fail(IErrCodeEnum errCode, String errMsg) {
        return new R<>(null, errMsg, errCode.getCode());
    }

    public static <T> R<T> fail(T data, IErrCodeEnum errCode, String errMsg) {
        return new R<>(data, errMsg, errCode.getCode());
    }

}
