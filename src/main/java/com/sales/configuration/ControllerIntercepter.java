package com.sales.configuration;

import com.sales.common.ThreadVariables;
import com.sales.common.ApplicationLogConstant;
import com.sales.presentation.LoggingController;
import com.sales.presentation.dto.LoggingCreateRequest;
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

    private final LoggingController loggingController;
    private final String[] ignoreControllers = {"com.sales.application.LoggingServiceImpl"};

    @Autowired
    public ControllerIntercepter(LoggingController loggingController) {
        this.loggingController = loggingController;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.getSession().setAttribute("CURRENT_URI", request.getRequestURI());

        if (request.getSession().getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            this.setUserDataToThreadVariables(request);
        }

        if (isLoggingController(this.getProcessName(handler) + "." + this.getMethodName(handler))) {
            printLog(handler, ApplicationLogConstant.INTERCEPT_POINT.PRE_HANDLE.getValue());
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) {
        if (isLoggingController(this.getProcessName(handler) + "." + this.getMethodName(handler))) {
            printLog(handler, ApplicationLogConstant.INTERCEPT_POINT.POST_HANDLE.getValue());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) {
        if (request.getSession().getAttribute("CURRENT_URI") != null) {
            request.getSession().setAttribute("PREVIOUS_URI", request.getSession().getAttribute("CURRENT_URI").toString());
        }

        if (isLoggingController(this.getProcessName(handler) + "." + this.getMethodName(handler))) {
            printLog(handler, ApplicationLogConstant.INTERCEPT_POINT.AFTER_COMPLETION.getValue());
        }
    }

    private boolean isLoggingController(String controller) {
        for (String ignore: this.ignoreControllers) {
            if (controller.contains(ignore)) return false;
        }
        return true;
    }

    private void printLog(Object handler, String interceptPoint) {
        LoggingCreateRequest loggingCreateRequest = new LoggingCreateRequest();

        loggingCreateRequest.setThreadNo(String.valueOf(Thread.currentThread().getId()));
        loggingCreateRequest.setRowNumber(String.valueOf(ThreadVariables.threadLocal.get().getLogRowNumberInThisThread()));
        loggingCreateRequest.setLogType(ApplicationLogConstant.LOG_TYPE.ACCESS.getValue());
        loggingCreateRequest.setInterceptPoint(interceptPoint);
        loggingCreateRequest.setUserId(ThreadVariables.threadLocal.get().getUserId());
        loggingCreateRequest.setSessionId(ThreadVariables.threadLocal.get().getSessionId());
        loggingCreateRequest.setProcessName(
                this.getProcessName(handler) + "." +
                this.getMethodName(handler) +
                "(" + this.getParameterType(handler) + ")");
        loggingCreateRequest.setProcessReturnType(this.getReturnType(handler));
        loggingCreateRequest.setArgumentValue(this.getParameterName(handler));

        ArrayList<LoggingCreateRequest> loggingArray = new ArrayList<>();
        loggingArray.add(loggingCreateRequest);

        this.loggingController.create(loggingArray);
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
