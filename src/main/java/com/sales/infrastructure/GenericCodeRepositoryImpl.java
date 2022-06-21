package com.sales.infrastructure;

import com.sales.domain.genericcode.Constant;
import com.sales.domain.genericcode.GenericCodeDomainService;
import com.sales.domain.genericcode.GenericCodeRepository;
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
public class GenericCodeRepositoryImpl extends AbstractBaseSystemDbRepository implements GenericCodeRepository {
    private final String APPLSQL008;

    @Autowired
    public GenericCodeRepositoryImpl(@Qualifier("appljdbc01") JdbcTemplate jdbcTemplate,
                                     @Qualifier("applNpjdbc01") NamedParameterJdbcTemplate npJdbcTemplate,
                                     @Value("${APPLSQL008}") String applsql008) {
        super(jdbcTemplate, npJdbcTemplate);
        this.APPLSQL008 = applsql008;
    }

    @Override
    public List<Map<String, Object>> findGenericCodeByCategoryOrder1(GenericCodeDomainService genericCodeDomainService) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_GENERIC_CD.CATEGORY.getValue(), genericCodeDomainService.getCategory());

//        super.loggingDelegateRepository.loggingDbDebugPoint(this.getClass(), new Object(){}.getClass().getEnclosingMethod(), "APPLSQL008",this.APPLSQL008 ,paramMap);
        List<Map<String, Object>> resultList = npJdbcTemplate.queryForList(APPLSQL008, paramMap);

        return resultList;
    }
}
