package com.managiment.presentation;

import com.managiment.application.SearchAuthUserService;
import com.managiment.application.SearchEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PropertySource(value = "classpath:properties/sql.properties")
public class SerchEmployee {

    @Autowired
    private SearchEmployeeService searchEmployeeService;

    @Autowired
    private SearchAuthUserService searchAuthUserService;

    @Autowired @Qualifier("secondaryjdbc")
    private JdbcTemplate jdbcTemplate2;

    @Autowired @Qualifier("secondaryNpjdbc")
    private NamedParameterJdbcTemplate npJdbcTemplate2;

    @Value("${SQLA001}")
    String sql21;
    @Value("${SQLA002}")
    String sql22;

    @RequestMapping("/serch")
    private String serch(){
        this.searchEmployeeService.searchEmployee();

//        List<Map<String, Object>> list21 = jdbcTemplate2.queryForList(sql21);
//        System.out.println("sql21 : " + list21);
//
//        Map<String, Object> paramMap2 = new HashMap<String, Object>();
//        paramMap2.put("ID", 101);
//        paramMap2.put("NEN", 2005);
//        paramMap2.put("SEI", "男");
//        List<Map<String, Object>> list22 = npJdbcTemplate2.queryForList(sql22, paramMap2);
//        System.out.println("sql22 : " + list22);

        this.searchAuthUserService.searchAuthUser();

        return "serch.html";
    }
}
