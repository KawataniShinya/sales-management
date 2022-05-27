package com.sales.infrastructure;

import com.sales.domain.test.AccessTestApplRepository;
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
public class AccessTestApplRepositoryImpl implements AccessTestApplRepository {

    @Autowired @Qualifier("appljdbc01")
    private JdbcTemplate jdbcTemplate;

    @Autowired @Qualifier("applNpjdbc01")
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

    @Override
    public void insertUser() {
//        this.npJdbcTemplate.update("insert into shain_table(id,name,sei,nen,address) values('900','藤田信正','男','2020','大阪府大阪市')", new HashMap<String, Object>());
    }
}
