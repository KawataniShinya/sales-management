package com.sales.configuration;

import com.sales.common.ThreadVariables;
import com.sales.common.logging.ApplicationLog;
import com.sales.common.logging.Constant;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private ApplicationLog applicationLog;
    private final String[] ignoreAspects = {"com.sales.infrastructure.ApplicationLogRepositoryImpl.insertLog(ApplicationLog)"};

//    private static ThreadLocal<ThreadVariables> threadValiable = new ThreadLocal<ThreadVariables>(){
//        @Override
//        protected ThreadVariables initialValue(){
//            return new ThreadVariables();
//        }
//    };

    @Autowired
    public LoggingAspect(ApplicationLog applicationLog) {
        this.applicationLog = applicationLog;
    }

    @Before("execution(* com.sales.application.*.*(..)) || execution(* com.sales.domain..*.*(..)) || execution(* com.sales.infrastructure.*.*(..))")
    public void loggingBefore(JoinPoint jp) {
        if (!Arrays.asList(this.ignoreAspects).contains(jp.getSignature().toString().split(" ")[1])) {
            printLog(jp, Constant.INTERCEPT_POINT.PRE_SERVICE.getValue());
        }
    }

    @After("execution(* com.sales.application.*.*(..)) || execution(* com.sales.domain..*.*(..)) || execution(* com.sales.infrastructure.*.*(..))")
    public void loggingAfter(JoinPoint jp) {
        if (!Arrays.asList(this.ignoreAspects).contains(jp.getSignature().toString().split(" ")[1])) {
            printLog(jp, Constant.INTERCEPT_POINT.POST_SERVICE.getValue());
        }
    }

    private void printLog(JoinPoint jp, String interceptPoint) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName = "";
//        if (authentication != null) {
//            Object principal = authentication.getPrincipal();
//            if (principal instanceof UserDetails) {
//                userName = ( (UserDetails) principal ).getUsername();
//            }
//        }

        this.applicationLog = this.applicationLog.createApplicationLog();

        this.applicationLog.setThreadNo(Thread.currentThread().getId());
        this.applicationLog.setRowNumber(ThreadVariables.threadLocal.get().getLogRowNumberInThisThread());
        this.applicationLog.setLogType(Constant.LOG_TYPE.ASPECT.getValue());
        this.applicationLog.setInterceptPoint(interceptPoint);
        this.applicationLog.setUserId(getUserName());
        this.applicationLog.setProcessName(jp.getSignature().toString().split(" ")[1]);
        this.applicationLog.setProcessReturnType(jp.getSignature().toString().split(" ")[0]);
        this.applicationLog.setArgumentValue(Arrays.toString(jp.getArgs()));

        this.applicationLog.outputLog();
    }

    private String getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = "";
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                userName = ( (UserDetails) principal ).getUsername();
            }
        }
        return userName;
    }
}
