package com.boot.commons.core.lock;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * LockAction
 *
 * @author XINAN
 * @date 2020/6/3
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LockAction {

    /**
     * 锁的资源，key。使用springEl"模板表达式,使用#{}作为定界符"; 写法参考:https://blog.csdn.net/keda8997110/article/details/52767087
     */
//    @AliasFor("key")
//    String[] value() default "'default'";
//
//    @AliasFor("value")
    String[] key() default "'default'";

    /**
     * 锁类型
     */
    LockType lockType() default LockType.RE_ENTRANT_LOCK;

    /**
     * 获取锁等待时间，默认3秒
     */
    long waitTime() default 3L;

    /**
     * 锁自动释放时间，默认30秒
     */
    long leaseTime() default 30L;

    /**
     * 时间单位（获取锁等待时间和持锁时间都用此单位）
     */
    TimeUnit unit() default TimeUnit.SECONDS;
}
