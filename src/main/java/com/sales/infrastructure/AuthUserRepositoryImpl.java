package com.sales.infrastructure;

import com.sales.common.LoggingDelegateRepository;
import com.sales.domain.auth.AuthUser;
import com.sales.domain.auth.AuthUserRepository;
import com.sales.domain.auth.Constant;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

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
    public List<Map<String, Object>> findEnableUserByUserId(AuthUser authUser) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_GENERIC_CD.CATEGORY.getValue(), Constant.CATEGORY.ROLE.getValue());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.USER_ID.getValue(), authUser.getUserId());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.EXPIRATION_DATE.getValue(), DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));

        super.loggingDelegateRepository.loggingDbDebugPoint(this.getClass(), new Object(){}.getClass().getEnclosingMethod(), "APPLSQL001", this.APPLSQL001 ,paramMap);
        return npJdbcTemplate.queryForList(APPLSQL001, paramMap);
    }
}
