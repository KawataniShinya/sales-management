package com.search;

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

    @RequestMapping("/result")
    private String result(@RequestParam String id, String pass, Model model){
        String name = "";
        if (id.equals("root") && pass.equals("password")){
            name = "ログイン太郎";
            model.addAttribute("status", "ログイン成功");
        } else {
            name = "no name";
            model.addAttribute("status", "ログイン失敗");
        }
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        return "result.html";
    }
}
