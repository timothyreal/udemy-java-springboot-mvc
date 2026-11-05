package com.training.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect 
public class DemoAopExpressions {
    @Pointcut("execution(* com.training.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {}

    @Pointcut ("execution(* com.training.aopdemo.dao.*.get*())")
    public void getter() {}

    @Pointcut ("execution(* com.training.aopdemo.dao.*.set*(..))")
    public void setter() {}

    @Pointcut ("forDaoPackage() && !(getter() && setter())")
    public void forDaoNoGetterAndSetter() {}
}
