package com.sales.infrastructure;

import com.sales.domain.logging.ApplicationLog;
import com.sales.domain.logging.ApplicationLogRepository;
import com.sales.common.ApplicationLogConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Scope("prototype")
@PropertySource(value = "classpath:properties/sql.properties")
public class ApplicationLogRepositoryImpl extends AbstractBaseSystemDbRepository implements ApplicationLogRepository {

    private final String SYSSQL001;

    @Autowired
    public ApplicationLogRepositoryImpl(@Qualifier("sysjdbc01") JdbcTemplate jdbcTemplate,
                                        @Qualifier("sysNpjdbc01") NamedParameterJdbcTemplate npJdbcTemplate,
                                        @Value("${SYSSQL001}") String SYSSQL001) {
        super(jdbcTemplate, npJdbcTemplate);
        this.SYSSQL001 = SYSSQL001;
    }

    @Override
    public void insertLog(ApplicationLog applicationLog) {
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put(ApplicationLogConstant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.INSERT_TIMESTAMP.getValue(), applicationLog.getInsertTimestamp());
        paramMap.put(ApplicationLogConstant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.THREAD_NO.getValue(), applicationLog.getThreadNo());
        paramMap.put(ApplicationLogConstant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.ROW_NUMBER.getValue(), applicationLog.getRowNumber());
        paramMap.put(ApplicationLogConstant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.LOG_TYPE.getValue(), applicationLog.getLogType());
        paramMap.put(ApplicationLogConstant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.INTERCEPT_POINT.getValue(), applicationLog.getInterceptPoint());
        paramMap.put(ApplicationLogConstant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.USER_ID.getValue(), applicationLog.getUserId());
        paramMap.put(ApplicationLogConstant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.SESSION_ID.getValue(), applicationLog.getSessionId());
        paramMap.put(ApplicationLogConstant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.PROCESS_NAME.getValue(), applicationLog.getProcessName());
        paramMap.put(ApplicationLogConstant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.PROCESS_RETURN_TYPE.getValue(), applicationLog.getProcessReturnType());
        paramMap.put(ApplicationLogConstant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.ARGUMENT_VALUE.getValue(), applicationLog.getArgumentValue());
        paramMap.put(ApplicationLogConstant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.MESSAGE.getValue(), applicationLog.getMessage());
        npJdbcTemplate.update(SYSSQL001, paramMap);
    }
}
