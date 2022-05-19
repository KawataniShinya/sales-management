package com.sales.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.sales.application.*.*(..)) || execution(* com.sales.domain..*.*(..)) || execution(* com.sales.infrastructure.*.*(..))")
    public void loggingBefore(JoinPoint jp) {
        System.out.println(
                new Timestamp(System.currentTimeMillis()).toString() + " "
                + Thread.currentThread().getId() + " "
                + " [PreService] " + jp.getSignature().toString().split(" ")[0] + " "
                + jp.getSignature().toString().split(" ")[1] + " "
                + Arrays.toString(jp.getArgs()));
    }

    @After("execution(* com.sales.application.*.*(..)) || execution(* com.sales.domain..*.*(..)) || execution(* com.sales.infrastructure.*.*(..))")
    public void loggingAfter(JoinPoint jp) {
        System.out.println(
                new Timestamp(System.currentTimeMillis()).toString() + " "
                + Thread.currentThread().getId() + " "
                + " [PostService] " + jp.getSignature().toString().split(" ")[0] + " "
                + jp.getSignature().toString().split(" ")[1] + " "
                + Arrays.toString(jp.getArgs()));
    }
}