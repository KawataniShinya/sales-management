package com.sales.domain.logging;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;

@Service
@Scope("prototype")
public class ApplicationLogImpl implements ApplicationLog{

    private InsertTimestamp insertTimestamp;

    @Getter
    @Setter
    private long threadNo;

    @Getter
    @Setter
    private int rowNumber;

    @Getter
    @Setter
    private String logType;

    @Getter
    @Setter
    private String interceptPoint;

    @Getter
    @Setter
    private String userId;

    @Getter
    @Setter
    private String sessionId;

    @Getter
    @Setter
    private String processName;

    private ProcessReturnType processReturnType;

    @Getter
    @Setter
    private String argumentValue;

    @Getter
    @Setter
    private String message;

    private ApplicationLogRepository applicationLogRepository;

    @Autowired
    public ApplicationLogImpl(ApplicationLogRepository applicationLogRepository) {
        this.applicationLogRepository = applicationLogRepository;
    }

    @Override
    public void outputLog() {
        if (canOutputLog()) {
            setInsertTimestamp(new Timestamp(System.currentTimeMillis()).toString());
            this.applicationLogRepository.insertLog(this);
        } else {
            // Exception
        }
    }

    private boolean canOutputLog() {
        return true;
    }

    @Override
    public void setFieldsFromMap(Map<String, Object> map) {
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.INSERT_TIMESTAMP.getValue())) this.setInsertTimestamp(map.get(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.INSERT_TIMESTAMP.getValue()).toString());
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.THREAD_NO.getValue())) this.setThreadNo(Long.parseLong(map.get(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.THREAD_NO.getValue()).toString()));
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.ROW_NUMBER.getValue())) this.setRowNumber(Integer.parseInt(map.get(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.ROW_NUMBER.getValue()).toString()));
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.LOG_TYPE.getValue())) this.setLogType(map.get(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.LOG_TYPE.getValue()).toString());
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.INTERCEPT_POINT.getValue())) this.setInterceptPoint(map.get(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.INTERCEPT_POINT.getValue()).toString());
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.USER_ID.getValue())) this.setUserId(map.get(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.USER_ID.getValue()).toString());
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.SESSION_ID.getValue())) this.setSessionId(map.get(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.SESSION_ID.getValue()).toString());
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.PROCESS_NAME.getValue())) this.setProcessName(map.get(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.PROCESS_NAME.getValue()).toString());
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.PROCESS_RETURN_TYPE.getValue())) this.setProcessReturnType(map.get(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.PROCESS_RETURN_TYPE.getValue()).toString());
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.ARGUMENT_VALUE.getValue())) this.setArgumentValue(map.get(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.ARGUMENT_VALUE.getValue()).toString());
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.MESSAGE.getValue())) this.setMessage(map.get(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.MESSAGE.getValue()).toString());
    }

    public void setInsertTimestamp(String stringTimestamp) {
        InsertTimestamp insertTimestamp = new InsertTimestamp();
        insertTimestamp.setDate(stringTimestamp);
        this.insertTimestamp = insertTimestamp;
    }

    public Timestamp getInsertTimestamp() {
        return this.insertTimestamp.getDateFormatTimestamp();
    }

    public void setProcessReturnType(String value) {
        ProcessReturnType processReturnType = new ProcessReturnType();
        processReturnType.setValue(value, true);
        this.processReturnType = processReturnType;
    }

    public String getProcessReturnType() {
        return this.processReturnType.getValue();
    }
}
