package com.searchEmployee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Login {
    @RequestMapping("/login")
    private String login(){
        return "login.html";
    }

    @RequestMapping("/output")
    private String result(@RequestParam String id, Model model){
        String name = "コントローラー太郎";
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        return "output.html";
    }
}
