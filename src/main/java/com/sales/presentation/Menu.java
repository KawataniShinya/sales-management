package com.sales.presentation;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Menu {
    @RequestMapping("/menu")
    private String result(HttpServletRequest request, Model model){
        System.out.println("*** /result *** : " + request.getSession().getAttribute("SPRING_SECURITY_CONTEXT"));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("id", auth.getName());
        model.addAttribute("name", "test");
        return "menu.html";
    }
}
