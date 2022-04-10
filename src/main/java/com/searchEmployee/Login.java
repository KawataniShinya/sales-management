package com.searchEmployee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Login {
    @RequestMapping("/Login")
    private String login(){
        return "index";
    }
}
