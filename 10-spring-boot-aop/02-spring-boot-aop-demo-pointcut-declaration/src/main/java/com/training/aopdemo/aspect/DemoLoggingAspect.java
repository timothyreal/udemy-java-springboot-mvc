package com.training.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect 
@Component 
public class DemoLoggingAspect {

    @Pointcut("execution(* com.training.aopdemo.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut ("execution(* com.training.aopdemo.dao.*.get*())")
    private void getter() {}

    @Pointcut ("execution(* com.training.aopdemo.dao.*.set*(..))")
    private void setter() {}

    @Pointcut ("forDaoPackage() && !(getter() && setter())")
    private void forDaoNoGetterAndSetter() {}

    @Before ("forDaoPackage()")
    public void beforeAddAccount() {
        System.out.println("======>> Executing @Before advice on method");
    }

    @Before ("forDaoNoGetterAndSetter()")
    public void performApiAnalytics() {
        System.out.println("======>> Performing API Analytics");
    }

    @Before ("forDaoNoGetterAndSetter()")
    public void logToCloudAsync() {
        System.out.println("======>> Logging to cloud async aspect");
    }
}
