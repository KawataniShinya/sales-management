package com.sales.configuration;

import com.sales.common.ControllerIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
//    @Bean
//    ControllerIntercepter controllerIntercepter() {
//        return new ControllerIntercepter();
//    }

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
