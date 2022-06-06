package com.sales.infrastructure;

import com.sales.common.LoggingDelegateRepository;
import com.sales.domain.department.Constant;
import com.sales.domain.department.Department;
import com.sales.domain.department.DepartmentRepository;
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
public class DepartmentRepositoryImpl extends AbstractBaseApplicationDbRepository implements DepartmentRepository {
    private final String APPLSQL003;

    @Autowired
    public DepartmentRepositoryImpl(@Qualifier("appljdbc01") JdbcTemplate jdbcTemplate,
                                    @Qualifier("applNpjdbc01") NamedParameterJdbcTemplate npJdbcTemplate,
                                    LoggingDelegateRepository loggingDelegateRepository,
                                    @Value("${APPLSQL003}") String APPLSQL003) {
        super(jdbcTemplate, npJdbcTemplate, loggingDelegateRepository);
        this.APPLSQL003 = APPLSQL003;
    }

    @Override
    public List<Map<String, Object>> findByDepartmentCd(Department department) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_DEPARTMENT.DEPARTMENT_CD.getValue(), department.getDepartmentCd());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_DEPARTMENT.EXPIRATION_DATE.getValue(), DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));

        super.loggingDelegateRepository.loggingDbDebugPoint(this.getClass(), new Object(){}.getClass().getEnclosingMethod(), "APPLSQL003", this.APPLSQL003 ,paramMap);
        return npJdbcTemplate.queryForList(this.APPLSQL003, paramMap);
    }
}
