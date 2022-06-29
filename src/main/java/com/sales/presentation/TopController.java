package com.sales.presentation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Scope("prototype")
public class TopController {
    @RequestMapping("/top")
    public String init(HttpServletRequest request, Model model){
        CommonDisplay.setHeaderParameter(request, model);
        return "top.html";
    }
}
