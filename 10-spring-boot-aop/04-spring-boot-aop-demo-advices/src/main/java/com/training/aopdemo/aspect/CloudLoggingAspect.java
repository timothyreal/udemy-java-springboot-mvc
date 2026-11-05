package com.training.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect 
@Component 
@Order (1)
public class CloudLoggingAspect {
    
    @Before ("com.training.aopdemo.aspect.DemoAopExpressions.forDaoNoGetterAndSetter()")
    public void logToCloudAsync() {
        System.out.println("======>> Logging to cloud async aspect");
    }
}
