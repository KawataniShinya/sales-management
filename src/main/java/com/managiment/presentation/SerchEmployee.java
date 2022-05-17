package com.managiment.presentation;

import com.managiment.application.SearchApplUserService;
import com.managiment.application.SearchSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PropertySource(value = "classpath:properties/sql.properties")
public class SerchEmployee {

    @Autowired
    private SearchSysUserService searchSysUserService;

    @Autowired
    private SearchApplUserService searchApplUserService;

    @RequestMapping("/serch")
    private String serch(){
        this.searchSysUserService.searchSysUser();
        this.searchApplUserService.searchApplUser();

        return "serch.html";
    }
}
