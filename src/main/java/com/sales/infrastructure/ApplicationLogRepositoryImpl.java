package com.sales.infrastructure;

import com.sales.domain.logging.ApplicationLog;
import com.sales.domain.logging.ApplicationLogRepository;
import com.sales.domain.logging.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Repository
@Scope("prototype")
@PropertySource(value = "classpath:properties/sql.properties")
public class ApplicationLogRepositoryImpl extends AbstractBaseDbRepository implements ApplicationLogRepository {

    private String APL_LOG_INSERT_SQL;

    @Autowired
    public ApplicationLogRepositoryImpl(@Qualifier("appljdbc01") JdbcTemplate jdbcTemplate,
                                        @Qualifier("applNpjdbc01") NamedParameterJdbcTemplate npJdbcTemplate,
                                        @Value("${SYSSQL001}") String APL_LOG_INSERT_SQL) {
        super(jdbcTemplate, npJdbcTemplate);
        this.APL_LOG_INSERT_SQL = APL_LOG_INSERT_SQL;
    }

    @Override
    public void insertLog(ApplicationLog applicationLog) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (applicationLog.getTimestamp() != null) {
            paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.TIMESTAMP.getValue(), applicationLog.getTimestamp());
        } else {
            paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.TIMESTAMP.getValue(), new Timestamp(System.currentTimeMillis()).toString());
        }
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.THREAD_NO.getValue(), applicationLog.getThreadNo());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.ROW_NUMBER.getValue(), applicationLog.getRowNumber());
        if (applicationLog.getLogType() != null) {
            paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.LOG_TYPE.getValue(), applicationLog.getLogType());
        } else {
            paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.LOG_TYPE.getValue(), "");
        }
        if (applicationLog.getInterceptPoint() != null) {
            paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.INTERCEPT_POINT.getValue(), applicationLog.getInterceptPoint());
        } else {
            paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.INTERCEPT_POINT.getValue(), "");
        }
        if (applicationLog.getUserId() != null) {
            paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.USER_ID.getValue(), applicationLog.getUserId());
        } else {
            paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.USER_ID.getValue(), "");
        }
        if (applicationLog.getSessionId() != null) {
            paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.SESSION_ID.getValue(), applicationLog.getSessionId());
        } else {
            paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.SESSION_ID.getValue(), "");
        }
        if (applicationLog.getProcessName() != null) {
            paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.PROCESS_NAME.getValue(), applicationLog.getProcessName());
        } else {
            paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.PROCESS_NAME.getValue(), "");
        }
        if (applicationLog.getProcessReturnType() != null) {
            paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.PROCESS_RETURN_TYPE.getValue(), applicationLog.getProcessReturnType());
        } else {
            paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.PROCESS_RETURN_TYPE.getValue(), "");
        }
        if (applicationLog.getArgumentValue() != null) {
            paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.ARGUMENT_VALUE.getValue(), applicationLog.getArgumentValue());
        } else {
            paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.ARGUMENT_VALUE.getValue(), "");
        }
        if (applicationLog.getMessage() != null) {
            paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.MESSAGE.getValue(), applicationLog.getMessage());
        } else {
            paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.MESSAGE.getValue(), "");
        }
        npJdbcTemplate.update(APL_LOG_INSERT_SQL, paramMap);
    }
}
