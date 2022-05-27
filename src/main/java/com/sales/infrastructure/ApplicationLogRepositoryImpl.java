package com.sales.infrastructure;

import com.sales.common.logging.ApplicationLog;
import com.sales.common.logging.ApplicationLogRepository;
import com.sales.common.logging.Constant;
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
public class ApplicationLogRepositoryImpl extends AbstractBaseApplicationDbRepository implements ApplicationLogRepository {

    private final String APL_LOG_INSERT_SQL;

    @Autowired
    public ApplicationLogRepositoryImpl(@Qualifier("sysjdbc01") JdbcTemplate jdbcTemplate,
                                        @Qualifier("sysNpjdbc01") NamedParameterJdbcTemplate npJdbcTemplate,
                                        @Value("${SYSSQL001}") String APL_LOG_INSERT_SQL) {
        super(jdbcTemplate, npJdbcTemplate);
        this.APL_LOG_INSERT_SQL = APL_LOG_INSERT_SQL;
    }

    @Override
    public void insertLog(ApplicationLog applicationLog) {
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.INSERT_TIMESTAMP.getValue(), applicationLog.getInsertTimestamp());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.THREAD_NO.getValue(), applicationLog.getThreadNo());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.ROW_NUMBER.getValue(), applicationLog.getRowNumber());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.LOG_TYPE.getValue(), applicationLog.getLogType());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.INTERCEPT_POINT.getValue(), applicationLog.getInterceptPoint());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.USER_ID.getValue(), applicationLog.getUserId());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.SESSION_ID.getValue(), applicationLog.getSessionId());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.PROCESS_NAME.getValue(), applicationLog.getProcessName());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.PROCESS_RETURN_TYPE.getValue(), applicationLog.getProcessReturnType());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.ARGUMENT_VALUE.getValue(), applicationLog.getArgumentValue());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.MESSAGE.getValue(), applicationLog.getMessage());
        npJdbcTemplate.update(APL_LOG_INSERT_SQL, paramMap);
    }
}
