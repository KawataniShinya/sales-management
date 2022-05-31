package com.sales.configuration;

import com.sales.common.ThreadVariables;
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
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class ControllerIntercepter implements HandlerInterceptor {

    private ApplicationLog applicationLog;
    private final String[] ignoreControllers = {};

    @Autowired
    public ControllerIntercepter(ApplicationLog applicationLog) {
        this.applicationLog = applicationLog;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.getSession().setAttribute("CURRENT_URI", request.getRequestURI());

        if (request.getSession().getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            this.setUserDataToThreadVariables(request);
        }

        if (!Arrays.asList(this.ignoreControllers).contains(this.getProcessName(handler) + "." + this.getMethodName(handler))) {
            printLog(handler, Constant.INTERCEPT_POINT.PRE_HANDLE.getValue());
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) {
        if (!Arrays.asList(this.ignoreControllers).contains(this.getProcessName(handler) + "." + this.getMethodName(handler))) {
            printLog(handler, Constant.INTERCEPT_POINT.POST_HANDLE.getValue());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) {
        if (request.getSession().getAttribute("CURRENT_URI") != null) {
            request.getSession().setAttribute("PREVIOUS_URI", request.getSession().getAttribute("CURRENT_URI").toString());
        }

        if (!Arrays.asList(this.ignoreControllers).contains(this.getProcessName(handler) + "." + this.getMethodName(handler))) {
            printLog(handler, Constant.INTERCEPT_POINT.AFTER_COMPLETION.getValue());
        }
    }

    private void printLog(Object handler, String interceptPoint) {
        this.applicationLog = this.applicationLog.createApplicationLog();

        this.applicationLog.setThreadNo(Thread.currentThread().getId());
        this.applicationLog.setRowNumber(ThreadVariables.threadLocal.get().getLogRowNumberInThisThread());
        this.applicationLog.setLogType(Constant.LOG_TYPE.ACCESS.getValue());
        this.applicationLog.setInterceptPoint(interceptPoint);
        this.applicationLog.setUserId(ThreadVariables.threadLocal.get().getUserId());
        this.applicationLog.setSessionId(ThreadVariables.threadLocal.get().getSessionId());
        this.applicationLog.setProcessName(this.getProcessName(handler) + "." + this.getMethodName(handler));
        this.applicationLog.setProcessReturnType(this.getReturnType(handler) + "(" + this.getParameterType(handler) + ")");
        this.applicationLog.setArgumentValue(this.getParameterName(handler));

        if (!Arrays.asList(this.ignoreControllers).contains(this.applicationLog.getProcessName())) {
            this.applicationLog.outputLog();
        }
    }

    private void setUserDataToThreadVariables(HttpServletRequest request) {
        ThreadVariables.threadLocal.get().setUserId(this.getUserName());
        ThreadVariables.threadLocal.get().setRole(this.getRole());
        ThreadVariables.threadLocal.get().setSessionId(request.getSession().getId());
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

    private String getRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = "";
        if (authentication != null) {
            role = new ArrayList<>(SecurityContextHolder.getContext().getAuthentication().getAuthorities()).get(0).toString();
        }
        return role;
    }

    private String getProcessName(Object handler) {
        String processName = "";
        if (handler instanceof HandlerMethod handlerMethod) {
            processName = handlerMethod.getBeanType().getName();
        }
        return processName;
    }

    private String getMethodName(Object handler) {
        String methodName = "";
        if (handler instanceof HandlerMethod handlerMethod) {
            methodName = handlerMethod.getMethod().getName();
        }
        return methodName;
    }

    private String getReturnType(Object handler) {
        String returnType = "";
        if (handler instanceof HandlerMethod handlerMethod) {
            returnType = handlerMethod.getBeanType().toString().split(" ")[0];
        }
        return returnType;
    }

    private String getParameterType(Object handler) {
        StringBuilder sbParameterType = new StringBuilder();
        if (handler instanceof HandlerMethod handlerMethod) {
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
        if (handler instanceof HandlerMethod handlerMethod) {
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
