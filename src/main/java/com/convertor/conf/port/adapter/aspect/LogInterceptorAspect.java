package com.convertor.conf.port.adapter.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Map;

/**
 * @author neelam
 *
 */

@Aspect
@Component
public class LogInterceptorAspect {

    /**
     * Pointcut to execute on all the methods of classes.
     */
    @Pointcut("execution(public * *(..))")
    public void anyPublicMethod() {
        // Empty method for pointcut
    }

    @Around("anyPublicMethod() && @annotation(loggable)")
    public Object aroundLoggableMethods(final ProceedingJoinPoint joinPoint, final Loggable loggable) throws Throwable {
        return log(joinPoint, loggable);
    }

    @SuppressWarnings("rawtypes")
    public Object log(final ProceedingJoinPoint joinPoint, final Loggable loggable) throws Throwable {
        getLogger(joinPoint).info("start [{}], duration [{}]", joinPoint.getSignature().getName(), loggable.duration());

        final StopWatch sw = new StopWatch();
        Object returnVal;
        try {
            sw.start();
            returnVal = joinPoint.proceed();
        } finally {
            sw.stop();
        }
        if (returnVal instanceof Map) {
            getLogger(joinPoint).info("return value: [{}], duration: [{}], size: [{}]",
                    joinPoint.getSignature().getName(), sw.getTotalTimeMillis(), ((Map) returnVal).size());
        } else {
            getLogger(joinPoint).info("return value: [{}], duration: [{}]", joinPoint.getSignature().getName(),
                    sw.getTotalTimeMillis());
        }

        return returnVal;
    }

    private Logger getLogger(final JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
    }
}
