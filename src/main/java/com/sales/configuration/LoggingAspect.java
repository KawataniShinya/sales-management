package com.sales.configuration;

import com.sales.common.ThreadVariables;
import com.sales.domain.logging.ApplicationLog;
import com.sales.domain.logging.Constant;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private ApplicationLog applicationLog;
    private final String[] ignoreAspects = {
            "com.sales.infrastructure.ApplicationLogRepositoryImpl.insertLog(ApplicationLog)",
            "com.sales.domain.logging"};

    @Autowired
    public LoggingAspect(ApplicationLog applicationLog) {
        this.applicationLog = applicationLog;
    }

    @Before("execution(* com.sales.application.*.*(..)) || execution(* com.sales.domain..*.*(..)) || execution(* com.sales.infrastructure.*.*(..))")
    public void loggingBefore(JoinPoint jp) {
        if (isLoggingJointPoint(jp)) {
            printLog(jp, Constant.INTERCEPT_POINT.PRE_SERVICE.getValue());
        }
    }

    @After("execution(* com.sales.application.*.*(..)) || execution(* com.sales.domain..*.*(..)) || execution(* com.sales.infrastructure.*.*(..))")
    public void loggingAfter(JoinPoint jp) {
        if (isLoggingJointPoint(jp)) {
            printLog(jp, Constant.INTERCEPT_POINT.POST_SERVICE.getValue());
        }

    }

    private boolean isLoggingJointPoint(JoinPoint jp) {
        for (String ignore: this.ignoreAspects) {
            if (jp.getSignature().toString().split(" ")[1].contains(ignore)) return false;
        }
        return true;
    }

    private void printLog(JoinPoint jp, String interceptPoint) {
        this.applicationLog = this.applicationLog.createApplicationLog();

        this.applicationLog.setThreadNo(Thread.currentThread().getId());
        this.applicationLog.setRowNumber(ThreadVariables.threadLocal.get().getLogRowNumberInThisThread());
        this.applicationLog.setLogType(Constant.LOG_TYPE.ASPECT.getValue());
        this.applicationLog.setInterceptPoint(interceptPoint);
        this.applicationLog.setUserId(ThreadVariables.threadLocal.get().getUserId());
        this.applicationLog.setSessionId(ThreadVariables.threadLocal.get().getSessionId());
        this.applicationLog.setProcessName(jp.getSignature().toString().split(" ")[1]);
        this.applicationLog.setProcessReturnType(jp.getSignature().toString().split(" ")[0]);
        this.applicationLog.setArgumentValue(Arrays.toString(jp.getArgs()));

        this.applicationLog.outputLog();
    }

}
