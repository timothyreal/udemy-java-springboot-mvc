package com.training.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect 
@Component 
@Order (3)
public class ApiAnalyticsAspect {

    @Before ("com.training.aopdemo.aspect.DemoAopExpressions.forDaoNoGetterAndSetter()")
    public void performApiAnalytics() {
        System.out.println("======>> Performing API Analytics");
    }
}
