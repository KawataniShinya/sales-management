package com.sales.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    ControllerIntercepter controllerIntercepter;

    @Autowired
    public WebMvcConfig(ControllerIntercepter controllerIntercepter) {
        this.controllerIntercepter = controllerIntercepter;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(controllerIntercepter);
    }
}
