package com.training.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect 
@Component 
public class DemoLoggingAspect {

    @Before ("execution(* com.training.aopdemo.dao.*.*(..))")
    public void beforeAddAccount() {
        System.out.println("======>> Executing @Before advice on method");
    }

}
