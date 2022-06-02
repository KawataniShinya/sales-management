package com.sales.configuration;

import com.sales.common.ThreadVariables;
import com.sales.common.ApplicationLogConstant;
import com.sales.presentation.LoggingController;
import com.sales.presentation.dto.LoggingCreateRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private final LoggingController loggingController;
    private final String[] ignoreAspects = {
            "com.sales.infrastructure.ApplicationLogRepositoryImpl.insertLog(ApplicationLog)",
            "com.sales.domain.logging",
            "com.sales.application.LoggingServiceImpl"};

    @Autowired
    public LoggingAspect(LoggingController loggingController) {
        this.loggingController = loggingController;
    }

    @Before("execution(* com.sales.application.*.*(..)) || execution(* com.sales.domain..*.*(..)) || execution(* com.sales.infrastructure.*.*(..))")
    public void loggingBefore(JoinPoint jp) {
        if (isLoggingJointPoint(jp)) {
            printLog(jp, ApplicationLogConstant.INTERCEPT_POINT.PRE_SERVICE.getValue());
        }
    }

    @After("execution(* com.sales.application.*.*(..)) || execution(* com.sales.domain..*.*(..)) || execution(* com.sales.infrastructure.*.*(..))")
    public void loggingAfter(JoinPoint jp) {
        if (isLoggingJointPoint(jp)) {
            printLog(jp, ApplicationLogConstant.INTERCEPT_POINT.POST_SERVICE.getValue());
        }

    }

    private boolean isLoggingJointPoint(JoinPoint jp) {
        for (String ignore: this.ignoreAspects) {
            if (jp.getSignature().toString().split(" ")[1].contains(ignore)) return false;
        }
        return true;
    }

    private void printLog(JoinPoint jp, String interceptPoint) {
        LoggingCreateRequest loggingCreateRequest = new LoggingCreateRequest();

        loggingCreateRequest.setThreadNo(String.valueOf(Thread.currentThread().getId()));
        loggingCreateRequest.setRowNumber(String.valueOf(ThreadVariables.threadLocal.get().getLogRowNumberInThisThread()));
        loggingCreateRequest.setLogType(ApplicationLogConstant.LOG_TYPE.ASPECT.getValue());
        loggingCreateRequest.setInterceptPoint(interceptPoint);
        loggingCreateRequest.setUserId(ThreadVariables.threadLocal.get().getUserId());
        loggingCreateRequest.setSessionId(ThreadVariables.threadLocal.get().getSessionId());
        loggingCreateRequest.setProcessName(jp.getSignature().toString().split(" ")[1]);
        loggingCreateRequest.setProcessReturnType(jp.getSignature().toString().split(" ")[0]);
        loggingCreateRequest.setArgumentValue(Arrays.toString(jp.getArgs()));

        ArrayList<LoggingCreateRequest> loggingArray = new ArrayList<>();
        loggingArray.add(loggingCreateRequest);

        this.loggingController.create(loggingArray);
    }

}
