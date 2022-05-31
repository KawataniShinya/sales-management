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

    private LogType logType;

    private InterceptPoint interceptPoint;

    private UserId userId;

    private SessionId sessionId;

    private ProcessName processName;

    private ProcessReturnType processReturnType;

    private ArgumentValue argumentValue;

    private Message message;

    private final ApplicationLogRepository applicationLogRepository;

    @Autowired
    public ApplicationLogImpl(ApplicationLogRepository applicationLogRepository) {
        this.applicationLogRepository = applicationLogRepository;
        this.init();
    }

    @Override
    public ApplicationLog createApplicationLog() {
        return new ApplicationLogImpl(this.applicationLogRepository);
    }

    @Override
    public void init() {
        this.insertTimestamp = new InsertTimestamp();
        this.threadNo = 0;
        this.rowNumber = 0;
        this.logType = new LogType();
        this.interceptPoint = new InterceptPoint();
        this.userId = new UserId();
        this.sessionId = new SessionId();
        this.processName = new ProcessName();
        this.processReturnType = new ProcessReturnType();
        this.argumentValue = new ArgumentValue();
        this.message = new Message();
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

    public void setInsertTimestamp(String value) {
        InsertTimestamp insertTimestamp = new InsertTimestamp();
        insertTimestamp.setDate(value);
        this.insertTimestamp = insertTimestamp;
    }

    public Timestamp getInsertTimestamp() {
        return this.insertTimestamp.getDateFormatTimestamp();
    }

    public void setLogType(String value) {
        LogType logType = new LogType();
        logType.setValue(value, true);
        this.logType = logType;
    }

    public String getLogType() {
        return this.logType.getValue();
    }

    public void setInterceptPoint(String value) {
        InterceptPoint interceptPoint = new InterceptPoint();
        interceptPoint.setValue(value, true);
        this.interceptPoint = interceptPoint;
    }

    public String getInterceptPoint() {
        return this.interceptPoint.getValue();
    }

    public void setUserId(String value) {
        UserId userId = new UserId();
        userId.setValue(value, true);
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId.getValue();
    }

    public void setSessionId(String value) {
        SessionId sessionId = new SessionId();
        sessionId.setValue(value, true);
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return this.sessionId.getValue();
    }

    public void setProcessName(String value) {
        ProcessName processName = new ProcessName();
        processName.setValue(value, true);
        this.processName = processName;
    }

    public String getProcessName() {
        return this.processName.getValue();
    }

    public void setProcessReturnType(String value) {
        ProcessReturnType processReturnType = new ProcessReturnType();
        processReturnType.setValue(value, true);
        this.processReturnType = processReturnType;
    }

    public String getProcessReturnType() {
        return this.processReturnType.getValue();
    }

    public void setArgumentValue(String value) {
        ArgumentValue argumentValue = new ArgumentValue();
        argumentValue.setValue(value, true);
        this.argumentValue = argumentValue;
    }

    public String getArgumentValue() {
        return this.argumentValue.getValue();
    }

    public void setMessage(String value) {
        Message message = new Message();
        message.setValue(value, true);
        this.message = message;
    }

    public String getMessage() {
        return this.message.getValue();
    }

    @Override
    public ApplicationLogImpl clone() {
        ApplicationLogImpl applicationLogImpl = new ApplicationLogImpl(this.applicationLogRepository);
        applicationLogImpl.insertTimestamp = this.insertTimestamp;
        applicationLogImpl.threadNo = this.threadNo;
        applicationLogImpl.rowNumber = this.rowNumber;
        applicationLogImpl.logType = this.logType;
        applicationLogImpl.interceptPoint = this.interceptPoint;
        applicationLogImpl.userId = this.userId;
        applicationLogImpl.sessionId = this.sessionId;
        applicationLogImpl.processName = this.processName;
        applicationLogImpl.processReturnType = this.processReturnType;
        applicationLogImpl.argumentValue = this.argumentValue;
        applicationLogImpl.message = this.message;
        return applicationLogImpl;
    }
}
