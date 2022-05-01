package com.search;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class Login {
    @RequestMapping("/login")
    private String login(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes){
//        HttpSession session = request.getSession(false);
//
//        if (session != null) {
//            redirectAttributes.addFlashAttribute("id", session.getAttribute("id"));
//            redirectAttributes.addFlashAttribute("pass", session.getAttribute("password"));
//            return "redirect:/redirectResult";
//        }
//
        System.out.println("*** /login *** : " + request.getSession().getAttribute("SPRING_SECURITY_CONTEXT"));
        if (request.getSession().getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            return "result.html";
        }
        return "login.html";
    }

//    @PostMapping("/result")
//    private String result(HttpServletRequest request, @RequestParam String id, String pass, Model model){
//
//        HttpSession session = request.getSession(true);
//
//        String name = "";
//        if (id.equals("root") && pass.equals("password")){
//            name = "ログイン太郎";
//            model.addAttribute("status", "ログイン成功");
//        } else {
//            return "redirect:/login-error";
//        }
//
//        session.setAttribute("id", id);
//        session.setAttribute("name", name);
//        session.setAttribute("password", pass);
//
//        model.addAttribute("id", id);
//        model.addAttribute("name", name);
//
//        return "result.html";
//    }

    @RequestMapping("/result")
    private String result(HttpServletRequest request, Model model){
        System.out.println("*** /result *** : " + request.getSession().getAttribute("SPRING_SECURITY_CONTEXT"));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("id", auth.getName());
        model.addAttribute("name", "test");
        return "result.html";
    }

//    @RequestMapping("/login-error")
//    private String loginError(HttpServletRequest request, Model model){
//        model.addAttribute("loginError",true);
//        return "login.html";
//    }
//
//    @RequestMapping("/redirect-result")
//    private String redirectResult(HttpServletRequest request, Model model){
//
//        HttpSession session = request.getSession(false);
//        model.addAttribute("status", "ログイン成功");
//        model.addAttribute("id", session.getAttribute("id"));
//        model.addAttribute("name", session.getAttribute("name"));
//
//        return "result.html";
//    }
//
//    @RequestMapping("/logout")
//    private String logout(HttpServletRequest request, Model model){
//        HttpSession session = request.getSession(false);
//        session.invalidate();
//        return "redirect:/login";
//    }
}
