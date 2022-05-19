package com.sales.presentation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@Scope("prototype")
public class Login {
    @RequestMapping("/login")
    private String login(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes){
        if (request.getSession().getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            return "menu.html";
        }
        return "login.html";
    }
}