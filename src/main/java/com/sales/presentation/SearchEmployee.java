package com.sales.presentation;

import com.sales.application.SearchApplUserService;
import com.sales.application.SearchSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
@PropertySource(value = "classpath:properties/sql.properties")
public class SearchEmployee {

    private SearchSysUserService searchSysUserService;
    private SearchApplUserService searchApplUserService;
    private GenericApplicationContext context;

    @Autowired
    public SearchEmployee(SearchSysUserService searchSysUserService,
                          SearchApplUserService searchApplUserService,
                          GenericApplicationContext context) {
        this.searchSysUserService = searchSysUserService;
        this.searchApplUserService = searchApplUserService;
        this.context = context;
    }

    @RequestMapping("/serch")
    private String serch(){
        context.getBeanFactory().getBeanNamesIterator().forEachRemaining(el -> {
            System.out.print(el.toString() + ", ");
        });

        this.searchSysUserService.searchSysUser();
        this.searchApplUserService.searchApplUser();

        return "serch.html";
    }
}
