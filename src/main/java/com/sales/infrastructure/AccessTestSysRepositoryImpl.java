package com.sales.infrastructure;

import com.sales.domain.test.AccessTestSysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Scope("prototype")
@PropertySource(value = "classpath:properties/sql.properties")
public class AccessTestSysRepositoryImpl implements AccessTestSysRepository {

    @Autowired @Qualifier("sysjdbc01")
    private JdbcTemplate jdbcTemplate;

    @Autowired @Qualifier("sysNpjdbc01")
    private NamedParameterJdbcTemplate npJdbcTemplate;

    @Value("${SQLA001}")
    private String sql21;

    @Value("${SQLA002}")
    private String sql22;

    @Override
    public List<Map<String, Object>> selectAll() {
        return jdbcTemplate.queryForList(sql21);
    }

    @Override
    public List<Map<String, Object>> selectLimit() {
        Map<String, Object> paramMap2 = new HashMap<String, Object>();
        paramMap2.put("ID", 101);
        paramMap2.put("NEN", 2005);
        paramMap2.put("SEI", "男");
        return npJdbcTemplate.queryForList(sql22, paramMap2);
    }
}
