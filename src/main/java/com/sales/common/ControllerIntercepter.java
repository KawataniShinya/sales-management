package com.sales.common;

import com.sales.domain.logging.ApplicationLog;
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
        if (HandlerMethod.class.isInstance(handler)) printLog(request, handler, "PreHandle");

        // ToDo
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                System.out.println(( (UserDetails) principal ).getUsername());
            }
        }

        return true;
    }

    private void printLog(HttpServletRequest request, Object handler, String lineHead) {
        HandlerMethod handlerMethod = HandlerMethod.class.cast(handler);

        StringBuilder sbParameterType = new StringBuilder();
        StringBuilder sbParameterName = new StringBuilder();
        Arrays.stream(handlerMethod.getMethodParameters()).forEach(methodParameter -> {
            if (sbParameterType.isEmpty()) {
                sbParameterType.append("(");
                sbParameterName.append("[");
            } else {
                sbParameterType.append(", ");
                sbParameterName.append(", ");
            }
            sbParameterType.append(methodParameter.getParameter().getType().getName());
            sbParameterName.append(methodParameter.getParameter().getName());
        });
        sbParameterType.append(")");
        sbParameterName.append("]");

        System.out.println(
                new Timestamp(System.currentTimeMillis()).toString() + " "
                + Thread.currentThread().getId() + " "
                + "[" + lineHead + "] "
                + handlerMethod.getBeanType().toString().split(" ")[0] + " "
                + handlerMethod.getBeanType().getName() + "."
                + handlerMethod.getMethod().getName()
                + sbParameterType.toString() + " "
                + sbParameterName.toString()
                + " , SESSTIONID : " + request.getSession().getId());
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        if (HandlerMethod.class.isInstance(handler)) printLog(request, handler, "PostHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        if (HandlerMethod.class.isInstance(handler)) printLog(request, handler, "AfterCompletion");
    }

}
