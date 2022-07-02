package com.sales.presentation;

import com.sales.application.AuthenticatedService;
import com.sales.common.SessionConstant;
import com.sales.common.ThreadVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@Scope("prototype")
public class LoginController {

    private final AuthenticatedService authenticatedService;

    @Autowired
    public LoginController(AuthenticatedService authenticatedService) {
        this.authenticatedService = authenticatedService;
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        if (request.getSession().getAttribute(SessionConstant.ATTRIBUTE.SECURITY_CONTEXT.getValue()) != null) {
            return "redirect:/authenticated";
        }
        return "login.html";
    }

    @RequestMapping("/authenticated")
    public String authenticated(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        if (request.getSession().getAttribute(SessionConstant.ATTRIBUTE.SECURITY_CONTEXT.getValue()) != null) {
            request.getSession().setAttribute(SessionConstant.ATTRIBUTE.USER_ID.getValue(), ThreadVariables.threadLocal.get().getUserId());
            request.getSession().setAttribute(SessionConstant.ATTRIBUTE.ROLE.getValue(), ThreadVariables.threadLocal.get().getRole());
            this.authenticatedService.setSessionAttribute(request.getSession());

            return "redirect:/top";
        }
        return "redirect:/login";
    }
}
