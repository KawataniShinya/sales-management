package com.asp.infrastructure;

import com.asp.domain.user.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@PropertySource(value = "classpath:properties/sql.properties")
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate npJdbcTemplate;

    @Value("${SQLL001}")
    private String sql11;

    @Value("${SQLL002}")
    private String sql12;

    @Override
    public List<Map<String, Object>> selectAll() {
        return jdbcTemplate.queryForList(sql11);
    }

    @Override
    public List<Map<String, Object>> selectLimit() {
        Map<String, String> paramMap1 = new HashMap<String, String>();
        paramMap1.put("ID", "100");
        paramMap1.put("NEN", "2003");

        return npJdbcTemplate.queryForList(sql12, paramMap1);
    }
}
