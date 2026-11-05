package com.training.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.training.aopdemo.Account;

@Aspect 
@Component 
@Order (2)
public class DemoLoggingAspect {

    private void convertAccountNamesToUpperCase(List<Account> result) {
        for (Account account : result) {
            String upperName = account.getName().toUpperCase();

            account.setName(upperName);
        }
    }

    @Before ("com.training.aopdemo.aspect.DemoAopExpressions.forDaoPackage()")
    public void beforeAddAccount(JoinPoint joinPoint) {
        System.out.println("======>> Executing @Before advice on method");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg);

            if (arg instanceof Account) {
                Account account = (Account) arg;

                System.out.println("Account name: " + account.getName());
                System.out.println("Account level: " + account.getLevel());
            }
        }
    }

    @AfterReturning (
        pointcut = "execution(* com.training.aopdemo.dao.AccountDAO.findAccounts(..))",
        returning = "result"
    )
    public void afterFindAccounts(JoinPoint joinPoint, List<Account> result) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("======>> Executing @AfterReturning on method: " + method);
        System.out.println("======>> Result: " + result);
        
        this.convertAccountNamesToUpperCase(result);
        System.out.println("======>> After Convert Result: " + result);
    }

    @AfterThrowing (
        pointcut = "execution(* com.training.aopdemo.dao.AccountDAO.findAccounts(..))",
        throwing = "exception"
    )
    public void afterThrowingFindAccounts(JoinPoint joinPoint, Throwable exception) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("======>> Executing @AfterThrowing on method: " + method);
        System.out.println("======>> Exception: " + exception);
    }

    @After("execution(* com.training.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAcconts(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("======>> Executing @After (finally) on method: " + method);
    }

    @Around ("execution(* com.training.aopdemo.service.TrafficFortuneService.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("======>> Executing @Around on method: " + method);

        long begin = System.currentTimeMillis();
        
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            System.out.println("======>> Do something...");
            System.out.println("======>> Error Happened, Rethrow exception");

            throw e;
        }
        
        long end = System.currentTimeMillis();

        long duration = end - begin;
        System.out.println("======>> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }
}
