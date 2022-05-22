package com.sales.configuration;

import com.sales.common.ThreadVariables;
import com.sales.common.logging.ApplicationLog;
import com.sales.common.logging.Constant;
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
import java.util.Arrays;

@Component
public class ControllerIntercepter implements HandlerInterceptor {

    private ApplicationLog applicationLog;
    private final String[] ignoreControllers = {};

//    private static ThreadLocal<ThreadVariable> threadValiable = new ThreadLocal<ThreadVariable>(){
//        @Override
//        protected ThreadVariable initialValue(){
//            return new ThreadVariable();
//        }
//    };

    @Autowired
    public ControllerIntercepter(ApplicationLog applicationLog) {
        this.applicationLog = applicationLog;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!Arrays.asList(this.ignoreControllers).contains(this.getProcessName(handler) + "." + this.getMethodName(handler))) {
            printLog(request, handler, Constant.INTERCEPT_POINT.PRE_HANDLE.getValue());
        };
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        if (!Arrays.asList(this.ignoreControllers).contains(this.getProcessName(handler) + "." + this.getMethodName(handler))) {
            printLog(request, handler, Constant.INTERCEPT_POINT.POST_HANDLE.getValue());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        if (!Arrays.asList(this.ignoreControllers).contains(this.getProcessName(handler) + "." + this.getMethodName(handler))) {
            printLog(request, handler, Constant.INTERCEPT_POINT.AFTER_COMPLETION.getValue());
        }
    }

    private void printLog(HttpServletRequest request, Object handler, String interceptPoint) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName = "";
//        if (authentication != null) {
//            Object principal = authentication.getPrincipal();
//            if (principal instanceof UserDetails) {
//                userName = ( (UserDetails) principal ).getUsername();
//            }
//        }
//
//        String processName = "";
//        String methodName = "";
//        String returnType = "";
//        StringBuilder sbParameterType = new StringBuilder();
//        StringBuilder sbParameterName = new StringBuilder();
//        if (HandlerMethod.class.isInstance(handler)) {
//            HandlerMethod handlerMethod = HandlerMethod.class.cast(handler);
//            processName = handlerMethod.getBeanType().getName();
//            methodName = handlerMethod.getMethod().getName();
//            returnType = handlerMethod.getBeanType().toString().split(" ")[0];
//            Arrays.stream(handlerMethod.getMethodParameters()).forEach(methodParameter -> {
//                if (!sbParameterType.isEmpty()) {
//                    sbParameterType.append(", ");
//                    sbParameterName.append(", ");
//                }
//                sbParameterType.append(methodParameter.getParameter().getType().getName());
//                sbParameterName.append(methodParameter.getParameter().getName());
//            });
//        }

        this.applicationLog = this.applicationLog.createApplicationLog();

        this.applicationLog.setThreadNo(Thread.currentThread().getId());
        this.applicationLog.setRowNumber(ThreadVariables.threadLocal.get().getLogRowNumberInThisThread());
        this.applicationLog.setLogType(Constant.LOG_TYPE.ACCESS.getValue());
        this.applicationLog.setInterceptPoint(interceptPoint);
        this.applicationLog.setUserId(this.getUserName());
        this.applicationLog.setSessionId(request.getSession().getId());
        this.applicationLog.setProcessName(this.getProcessName(handler) + "." + this.getMethodName(handler));
        this.applicationLog.setProcessReturnType(this.getReturnType(handler) + "(" + this.getParameterType(handler).toString() + ")");
        this.applicationLog.setArgumentValue(this.getParameterName(handler).toString());

        if (!Arrays.asList(this.ignoreControllers).contains(this.applicationLog.getProcessName())) {
            this.applicationLog.outputLog();
        }
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

    private String getProcessName(Object handler) {
        String processName = "";
        if (HandlerMethod.class.isInstance(handler)) {
            HandlerMethod handlerMethod = HandlerMethod.class.cast(handler);
            processName = handlerMethod.getBeanType().getName();
        }
        return processName;
    }

    private String getMethodName(Object handler) {
        String methodName = "";
        if (HandlerMethod.class.isInstance(handler)) {
            HandlerMethod handlerMethod = HandlerMethod.class.cast(handler);
            methodName = handlerMethod.getMethod().getName();
        }
        return methodName;
    }

    private String getReturnType(Object handler) {
        String returnType = "";
        if (HandlerMethod.class.isInstance(handler)) {
            HandlerMethod handlerMethod = HandlerMethod.class.cast(handler);
            returnType = handlerMethod.getBeanType().toString().split(" ")[0];
        }
        return returnType;
    }

    private String getParameterType(Object handler) {
        StringBuilder sbParameterType = new StringBuilder();
        if (HandlerMethod.class.isInstance(handler)) {
            HandlerMethod handlerMethod = HandlerMethod.class.cast(handler);
            Arrays.stream(handlerMethod.getMethodParameters()).forEach(methodParameter -> {
                if (!sbParameterType.isEmpty()) {
                    sbParameterType.append(", ");
                }
                sbParameterType.append(methodParameter.getParameter().getType().getName());
            });
        }
        return sbParameterType.toString();
    }

    private String getParameterName(Object handler) {
        StringBuilder sbParameterName = new StringBuilder();
        if (HandlerMethod.class.isInstance(handler)) {
            HandlerMethod handlerMethod = HandlerMethod.class.cast(handler);
            Arrays.stream(handlerMethod.getMethodParameters()).forEach(methodParameter -> {
                if (!sbParameterName.isEmpty()) {
                    sbParameterName.append(", ");
                }
                sbParameterName.append(methodParameter.getParameter().getName());
            });
        }
        return sbParameterName.toString();
    }
}
