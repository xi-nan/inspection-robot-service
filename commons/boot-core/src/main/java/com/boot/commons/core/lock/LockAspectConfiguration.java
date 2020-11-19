package com.boot.commons.core.lock;

import cn.hutool.log.StaticLog;
import com.boot.commons.core.exception.BusinessException;
import com.boot.commons.core.exception.enums.ErrCodeEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

@Aspect
@Configuration
@AutoConfigureAfter(RedissonAutoConfiguration.class)
@EnableAutoConfiguration
public class LockAspectConfiguration {

    private final ExpressionParser parser = new SpelExpressionParser();
    private final LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
    @Autowired
    private RedissonClient redissonClient;

    @Pointcut("@annotation(com.boot.commons.core.lock.LockAction)")
    private void lockPoint() {

    }

    @Around("lockPoint()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        LockAction lockAction = method.getAnnotation(LockAction.class);
        Object[] args = pjp.getArgs();
        Object[] keys = parse(lockAction.key(), method, args);

        RLock lock = null;
        if (1 < keys.length) {
            // 多个key 使用联锁
            RLock[] locks = new RLock[keys.length];
            for (int i = 0; i < keys.length; i++) {
                locks[i] = getLock(keys[i], lockAction);
            }
            lock = new RedissonMultiLock(locks);
            if (!lock.tryLock(lockAction.waitTime(), lockAction.leaseTime(), lockAction.unit())) {
                StaticLog.debug("is lock failed [{}]", keys);
                ErrCodeEnum.E_10020.throwThis();
            }
        } else if (1 == keys.length) {
            // 单个key 正常加锁
            lock = getLock(keys[0], lockAction);
            if (!lock.tryLock(lockAction.waitTime(), lockAction.leaseTime(), lockAction.unit())) {
                StaticLog.debug("is lock failed [{}]", keys[0]);
                ErrCodeEnum.E_10020.throwThis();
            }
        }

        //得到锁,执行方法，释放锁
        try {
            StaticLog.debug("is lock success [{}]", keys);
            return pjp.proceed();
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            StaticLog.error("execute locked method occured an exception", e);
            throw e;
        } finally {
            if (null != lock) {
                lock.unlock();
            }
            StaticLog.debug("release lock [{}]", keys);
        }
    }

    /**
     * @param keys   表达式
     * @param method 方法
     * @param args   方法参数
     * @return
     * @description 解析spring EL表达式
     */
    private String[] parse(String[] keys, Method method, Object[] args) {
        String[] keysParse = new String[keys.length];
        for (int i = 0; i < keys.length; i++) {
            String[] params = discoverer.getParameterNames(method);
            EvaluationContext context = new StandardEvaluationContext();
            if (null != params && 0 < params.length) {
                for (int j = 0; j < params.length; j++) {
                    context.setVariable(params[j], args[j]);
                }
            }
            keysParse[i] = parser.parseExpression(keys[i], new TemplateParserContext()).getValue(context, String.class);
        }
        return keysParse;
    }

    private RLock getLock(Object key, LockAction lockAction) {
        String keyStr = (String) key;
        switch (lockAction.lockType()) {
            case RE_ENTRANT_LOCK:
                return redissonClient.getLock(keyStr);
            case FAIR_LOCK:
                return redissonClient.getFairLock(keyStr);
            case READ_LOCK:
                return redissonClient.getReadWriteLock(keyStr).readLock();
            case WRITE_LOCK:
                return redissonClient.getReadWriteLock(keyStr).writeLock();
            default:
                throw new RuntimeException("do not support lock type:" + lockAction.lockType().name());
        }
    }

}

