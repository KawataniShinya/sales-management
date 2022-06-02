package com.sales.infrastructure;

import com.sales.common.ApplicationLogConstant;
import com.sales.common.LoggingDelegateRepository;
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

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Scope("prototype")
@PropertySource(value = "classpath:properties/sql.properties")
public class AuthUserRepositoryImpl extends AbstractBaseApplicationDbRepository implements AuthUserRepository {

    private final String APPLSQL001;

    @Autowired
    public AuthUserRepositoryImpl(@Qualifier("appljdbc01") JdbcTemplate jdbcTemplate,
                                  @Qualifier("applNpjdbc01") NamedParameterJdbcTemplate npJdbcTemplate,
                                  LoggingDelegateRepository loggingDelegateRepository,
                                  @Value("${APPLSQL001}") String APPLSQL001) {
        super(jdbcTemplate, npJdbcTemplate, loggingDelegateRepository);
        this.APPLSQL001 = APPLSQL001;
    }

    @Override
    public List<Map<String, Object>> findByUserId(AuthUser authUser) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_GENERIC_CD.CATEGORY.getValue(), Constant.CATEGORY.ROLE.getValue());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.USER_ID.getValue(), authUser.getUserId());

        super.loggingDelegateRepository.loggingDbDebugPoint(this.getClass(), new Object(){}.getClass().getEnclosingMethod(), "APPLSQL001",this.APPLSQL001 ,paramMap);
        return npJdbcTemplate.queryForList(APPLSQL001, paramMap);
    }
}
