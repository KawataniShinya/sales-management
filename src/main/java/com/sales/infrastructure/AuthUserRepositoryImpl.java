package com.sales.infrastructure;

import com.sales.domain.user.AuthUser;
import com.sales.domain.user.AuthUserRepository;
import com.sales.domain.user.Constant;
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
public class AuthUserRepositoryImpl extends AbstractBaseDbRepository implements AuthUserRepository {

    private String AUTH_SQL;

    @Autowired
    public AuthUserRepositoryImpl(@Qualifier("appljdbc01") JdbcTemplate jdbcTemplate,
                                  @Qualifier("applNpjdbc01") NamedParameterJdbcTemplate npJdbcTemplate,
                                  @Value("${APPLSQL001}") String AUTH_SQL) {
        super(jdbcTemplate, npJdbcTemplate);
        this.AUTH_SQL = AUTH_SQL;
    }

    @Override
    public List<Map<String, Object>> findByUserId(AuthUser authUser) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("CATEGORY", Constant.CATEGORY.ROLE.getValue());
        paramMap.put("ID", authUser.getId());

        List<Map<String, Object>> resultList = npJdbcTemplate.queryForList(AUTH_SQL, paramMap);

        return resultList;
    }
}
