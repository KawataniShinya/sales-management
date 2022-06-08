package com.sales.infrastructure;

import com.sales.common.LoggingDelegateRepository;
import com.sales.domain.customer.Constant;
import com.sales.domain.customer.Customer;
import com.sales.domain.customer.CustomerRepository;
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
public class CustomerRepositoryImpl extends AbstractBaseApplicationDbRepository implements CustomerRepository{
    private final String APPLSQL004;

    @Autowired
    public CustomerRepositoryImpl(
            @Qualifier("appljdbc01") JdbcTemplate jdbcTemplate,
            @Qualifier("applNpjdbc01") NamedParameterJdbcTemplate npJdbcTemplate,
            LoggingDelegateRepository loggingDelegateRepository,
            @Value("${APPLSQL004}") String APPLSQL004) {
        super(jdbcTemplate, npJdbcTemplate, loggingDelegateRepository);
        this.APPLSQL004 = APPLSQL004;
    }

    @Override
    public List<Map<String, Object>> findUserByUserIdInExpiration(Customer customer) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.USER_ID.getValue(), customer.getUserId());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.EXPIRATION_DATE.getValue(), DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_GENERIC_CD.CATEGORY_PERSONALITY.getValue(), Constant.CATEGORY.PERSONALITY.getValue());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_GENERIC_CD.CATEGORY_GENDER.getValue(), Constant.CATEGORY.GENDER.getValue());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_GENERIC_CD.CATEGORY_BLOOD_TYPE.getValue(), Constant.CATEGORY.BLOOD_TYPE.getValue());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_GENERIC_CD.CATEGORY_ADDRESS_PREFECTURE.getValue(), Constant.CATEGORY.ADDRESS_PREFECTURE.getValue());

        super.loggingDelegateRepository.loggingDbDebugPoint(this.getClass(), new Object(){}.getClass().getEnclosingMethod(), "APPLSQL004",this.APPLSQL004 ,paramMap);
        List<Map<String, Object>> resultList = npJdbcTemplate.queryForList(APPLSQL004, paramMap);
        return resultList;
    }
}
