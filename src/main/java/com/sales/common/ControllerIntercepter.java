package com.sales.common;

import com.sales.domain.logging.ApplicationLog;
import com.sales.domain.logging.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Arrays;

@Component
public class ControllerIntercepter implements HandlerInterceptor {

    private ApplicationLog applicationLog;

    @Autowired
    public ControllerIntercepter(ApplicationLog applicationLog) {
        this.applicationLog = applicationLog;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        printLog(request, handler, Constant.INTERCEPT_POINT.PRE_HANDLE.getValue());
        return true;
    }

    private void printLog(HttpServletRequest request, Object handler, String interceptPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = "";
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                userName = ( (UserDetails) principal ).getUsername();
            }
        }

        String processName = "";
        String methodName = "";
        String returnType = "";
        StringBuilder sbParameterType = new StringBuilder();
        StringBuilder sbParameterName = new StringBuilder();
        if (HandlerMethod.class.isInstance(handler)) {
            HandlerMethod handlerMethod = HandlerMethod.class.cast(handler);
            processName = handlerMethod.getBeanType().getName();
            methodName = handlerMethod.getMethod().getName();
            returnType = handlerMethod.getBeanType().toString().split(" ")[0];
            Arrays.stream(handlerMethod.getMethodParameters()).forEach(methodParameter -> {
                if (!sbParameterType.isEmpty()) {
                    sbParameterType.append(", ");
                    sbParameterName.append(", ");
                }
                sbParameterType.append(methodParameter.getParameter().getType().getName());
                sbParameterName.append(methodParameter.getParameter().getName());
            });
        }

        this.applicationLog.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());
        this.applicationLog.setThreadNo(Thread.currentThread().getId());
        this.applicationLog.setRowNumber(0);
        this.applicationLog.setLogType(Constant.LOG_TYPE.ACCESS.getValue());
        this.applicationLog.setInterceptPoint(interceptPoint);
        this.applicationLog.setUserId(userName);
        this.applicationLog.setSessionId(request.getSession().getId());
        this.applicationLog.setProcessName(processName + "." + methodName);
        this.applicationLog.setProcessReturnType(returnType + "(" + sbParameterType.toString() + ")");
        this.applicationLog.setArgumentValue(sbParameterName.toString());

        this.applicationLog.outputLog();

//        StringBuilder sbParameterType = new StringBuilder();
//        StringBuilder sbParameterName = new StringBuilder();
//        Arrays.stream(handlerMethod.getMethodParameters()).forEach(methodParameter -> {
//            if (sbParameterType.isEmpty()) {
//                sbParameterType.append("(");
//                sbParameterName.append("[");
//            } else {
//                sbParameterType.append(", ");
//                sbParameterName.append(", ");
//            }
//            sbParameterType.append(methodParameter.getParameter().getType().getName());
//            sbParameterName.append(methodParameter.getParameter().getName());
//        });
//        sbParameterType.append(")");
//        sbParameterName.append("]");
//
//        System.out.println(
//                new Timestamp(System.currentTimeMillis()).toString() + " "
//                + Thread.currentThread().getId() + " "
//                + "[" + interceptPoint + "] "
//                + handlerMethod.getBeanType().toString().split(" ")[0] + " "
//                + handlerMethod.getBeanType().getName() + "."
//                + handlerMethod.getMethod().getName()
//                + sbParameterType.toString() + " "
//                + sbParameterName.toString()
//                + " , SESSTIONID : " + request.getSession().getId());
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("**** postHandle ****");
        printLog(request, handler, Constant.INTERCEPT_POINT.POST_HANDLE.getValue());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        printLog(request, handler, "AfterCompletion");
        printLog(request, handler, Constant.INTERCEPT_POINT.AFTER_COMPLETION.getValue());
    }

}
