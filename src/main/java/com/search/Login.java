package com.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

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

        testBcryptMatch();

        return "result.html";
    }

    private void testBcryptMatch() {
        String password = "password";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String digest = passwordEncoder.encode(password);
        System.out.println("ハッシュ値 = " + digest);

        if (passwordEncoder.matches(password, digest)) {
            System.out.println("一致したよ : " + password + " = " + digest);
        } else {
            System.out.println("一致しなかったよ : " + password + " = " + digest);
        }

        digest = "$2a$08$uGieQ2B6N9Ic7666NIpgI.7I70oits21rmd3D1M1nhBWnG8Zsko7C";

        if (passwordEncoder.matches(password, digest)) {
            System.out.println("一致したよ : " + password + " = " + digest);
        } else {
            System.out.println("一致しなかったよ : " + password + " = " + digest);
        }
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
