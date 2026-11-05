package com.training.crud_employee.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect 
@Component 
public class DemoLoggingAspect {

    private Logger _logger = Logger.getLogger(getClass().getName());

    @Pointcut ("execution(* com.training.crud_employee.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut ("execution(* com.training.crud_employee.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut ("execution(* com.training.crud_employee.repository.*.*(..))")
    private void forRepositoryPackage() {}

    @Pointcut ("forControllerPackage() || forServicePackage() || forRepositoryPackage()")
    private void forAppFlow() {}

    @Before ("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        this._logger.info("======>> in @Before: calling method: " + method);

        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            this._logger.info("=======>> argument: "+ arg);
        }
    }

    @AfterReturning (
        pointcut = "forAppFlow()",
        returning = "result"
    )
    public void AfterReturning(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();
        this._logger.info("======>> in @AfterReturning: calling method: " + method);

        this._logger.info("=======>> result: " + result);
    }
}
