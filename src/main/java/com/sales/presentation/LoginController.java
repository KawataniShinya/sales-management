package com.sales.presentation;

import com.fasterxml.jackson.datatype.jsr310.ser.YearSerializer;
import com.sales.common.SessionConstant;
import com.sales.common.ThreadVariables;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@Scope("prototype")
public class LoginController {
    @RequestMapping("/login")
    private String login(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        if (request.getSession().getAttribute(SessionConstant.ATTRIBUTE.SECURITY_CONTEXT.getValue()) != null) {
//            request.getSession().getAttributeNames().asIterator().forEachRemaining(e -> System.out.println(e.toString()));
//            System.out.println(request.getSession().getAttribute("org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN"));

//            System.out.println(request.getSession().getAttribute("SPRING_SECURITY_CONTEXT"));

//            ArrayList<? extends GrantedAuthority> roleList = new ArrayList<>(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
//            roleList.forEach(role -> System.out.println(role.toString()));
//            System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
            return "redirect:/authenticated";
        }
        return "login.html";
    }

    @RequestMapping("/authenticated")
    private String authenticated(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
//        if (request == null) {
//            System.out.println("null");
//        } else {
//            System.out.println("not null");
//        }
        if (request.getSession().getAttribute(SessionConstant.ATTRIBUTE.SECURITY_CONTEXT.getValue()) != null) {
            request.getSession().setAttribute(SessionConstant.ATTRIBUTE.USER_ID.getValue(), ThreadVariables.threadLocal.get().getUserId());
            request.getSession().setAttribute(SessionConstant.ATTRIBUTE.ROLE.getValue(), ThreadVariables.threadLocal.get().getRole());

            return "redirect:/menu";
        }
        return "redirect:/login";
    }
}
