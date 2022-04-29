package com.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SerchEmployee {
    @Autowired
    private JdbcTemplate jdbcTemplate1;

    @Autowired
    private NamedParameterJdbcTemplate npJdbcTemplate1;

    @Autowired @Qualifier("secondaryjdbc")
    private JdbcTemplate jdbcTemplate2;

    @Autowired @Qualifier("secondaryNpjdbc")
    private NamedParameterJdbcTemplate npJdbcTemplate2;

    @RequestMapping("/serch")
    private String serch(){
        String sql11 = "SELECT * FROM shain_table";
        List<Map<String, Object>> list11 = jdbcTemplate1.queryForList(sql11);
        System.out.println("sql11 : " + list11);

        String sql12 = "SELECT * FROM shain_table where id<>:ID and nen<>:NEN";
        Map<String, String> paramMap1 = new HashMap<String, String>();
        paramMap1.put("ID", "100");
        paramMap1.put("NEN", "2003");
        List<Map<String, Object>> list12 = npJdbcTemplate1.queryForList(sql12, paramMap1);
        System.out.println("sql12 : " + list12);


        String sql21 = "SELECT * FROM t_mst_employee";
        List<Map<String, Object>> list21 = jdbcTemplate2.queryForList(sql21);
        System.out.println("sql21 : " + list21);

        String sql22 = "SELECT * FROM t_mst_employee where id<>:ID and nen<>:NEN and sei=:SEI";
        Map<String, Object> paramMap2 = new HashMap<String, Object>();
        paramMap2.put("ID", 101);
        paramMap2.put("NEN", 2005);
        paramMap2.put("SEI", "男");
        List<Map<String, Object>> list22 = npJdbcTemplate2.queryForList(sql22, paramMap2);
        System.out.println("sql22 : " + list22);

        return "serch.html";
    }
}
