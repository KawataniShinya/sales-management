package com.managiment.infrastructure;

import com.managiment.domain.user.AuthUser;
import com.managiment.domain.user.AuthUserRepository;
import com.managiment.domain.user.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class AuthUserRepositoryImpl implements AuthUserRepository {

    @Autowired @Qualifier("appljdbc01")
    private JdbcTemplate jdbcTemplate;

    @Autowired @Qualifier("applNpjdbc01")
    private NamedParameterJdbcTemplate npJdbcTemplate;

    @Value("${APPLSQL001}")
    private String AUTH_SQL;

    @Override
    public List<Map<String, Object>> findByUserId(AuthUser authUser) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("CATEGORY", Constant.CATEGORY.ROLE.getValue());
        paramMap.put("ID", authUser.getUserId());
        System.out.println(AUTH_SQL);
        System.out.println(paramMap);
        return npJdbcTemplate.queryForList(AUTH_SQL, paramMap);
    }
}
