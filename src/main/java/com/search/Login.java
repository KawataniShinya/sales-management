package com.search;

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
        HttpSession session = request.getSession(false);

        if (session != null) {
            redirectAttributes.addFlashAttribute("id", session.getAttribute("id"));
            redirectAttributes.addFlashAttribute("pass", session.getAttribute("password"));
            return "redirect:/redirectResult";
        }

        return "login.html";
    }

    @PostMapping("/result")
    private String result(HttpServletRequest request, @RequestParam String id, String pass, Model model){

        HttpSession session = request.getSession(true);

        String name = "";
        if (id.equals("root") && pass.equals("password")){
            name = "ログイン太郎";
            model.addAttribute("status", "ログイン成功");
        } else {
            name = "no name";
            model.addAttribute("status", "ログイン失敗");
        }

        session.setAttribute("id", id);
        session.setAttribute("name", name);
        session.setAttribute("password", pass);

        model.addAttribute("id", id);
        model.addAttribute("name", name);

        return "result.html";
    }

    @RequestMapping("/redirectResult")
    private String redirectResult(HttpServletRequest request, Model model){

        HttpSession session = request.getSession(false);
        model.addAttribute("status", "ログイン成功");
        model.addAttribute("id", session.getAttribute("id"));
        model.addAttribute("name", session.getAttribute("name"));

        return "result.html";
    }
}
