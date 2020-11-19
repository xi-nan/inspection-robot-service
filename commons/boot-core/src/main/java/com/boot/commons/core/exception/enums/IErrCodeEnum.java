package com.boot.commons.core.exception.enums;

import com.boot.commons.core.exception.BusinessException;

/**
 * IErrCodeEnum
 * <p>
 * 错误码code统一格式：ABBCC
 * A:错误级别，如1代表系统级错误(需要后端人员配合处理)，2代表服务级错误(前端可自行解决)
 * B:模块编号，项目负责人分配
 * C:具体错误编号，枚举内维护自增
 * <p>
 * 模块内建本模块使用的错误码枚举类
 * 默认错误码枚举：{@link ErrCodeEnum}
 *
 * @author XINAN
 * @date 2019/7/22
 */
public interface IErrCodeEnum {

    Integer getCode();

    String getDesc();

    default void throwIf(boolean expr, Object... msgArgs) {
        if (expr) {
            throw wrapThis(msgArgs);
        }
    }

    default BusinessException wrapThis(Object... msgArgs) {
        return new BusinessException(this, null, msgArgs);
    }

    default void throwThis(Object... msgArgs) {
        throw wrapThis(msgArgs);
    }

}