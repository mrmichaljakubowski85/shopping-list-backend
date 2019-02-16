package com.tomtre.shoppinglist.backend.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private final static Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("execution(* com.tomtre.shoppinglist.backend.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* com.tomtre.shoppinglist.backend.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("execution(* com.tomtre.shoppinglist.backend.dao.*.*(..))")
    private void forDaoPackage() {
    }

    @Pointcut("forDaoPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {}

    @Before("forAppFlow()")
    public void beforeAdvice(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        LOGGER.info("calling method: " + methodName);
        Object[] methodArgs = joinPoint.getArgs();
        for (int i = 0; i < methodArgs.length; i++) {
            LOGGER.info("methodArgs[" + i + "]: " + methodArgs[i]);
        }

    }
}
