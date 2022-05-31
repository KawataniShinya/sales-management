package com.sales.presentation;

import com.sales.common.ThreadVariables;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Scope("prototype")
public class Menu {
    @RequestMapping("/menu")
    private String result(HttpServletRequest request, Model model){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        model.addAttribute("id", auth.getName());
        model.addAttribute("userId", ThreadVariables.threadLocal.get().getUserId());
        model.addAttribute("name", "test");
        model.addAttribute("role", ThreadVariables.threadLocal.get().getRole());

        switch (ThreadVariables.threadLocal.get().getRole()) {
            case ROLE_USER :
            case ROLE_GUEST:
                return "menu_customer.html";
            case ROLE_ADMIN:
            case ROLE_STAFF:
                return "menu_inernal.html";
            default:
                return "Internal Error";
        }
    }
}
