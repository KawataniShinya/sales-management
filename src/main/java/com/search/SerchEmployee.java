package com.search;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SerchEmployee {
    @RequestMapping("/serch")
    private String serch(){
        return "serch.html";
    }
}
