package com.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SerchEmployee {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate npJdbcTemplate;

    @RequestMapping("/serch")
    private String serch(){
        String sql1 = "SELECT * FROM shain_table";
        List<Map<String, Object>> list1 = jdbcTemplate.queryForList(sql1);
        System.out.println(list1);

        String sql2 = "SELECT * FROM shain_table where id<>:ID and nen<>:NEN";
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("ID", "100");
        paramMap.put("NEN", "2003");
        List<Map<String, Object>> list2 = npJdbcTemplate.queryForList(sql2, paramMap);
        System.out.println(list2);

        return "serch.html";
    }
}
