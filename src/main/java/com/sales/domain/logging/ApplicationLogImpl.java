package com.sales.domain.logging;

import com.sales.common.ApplicationLogConstant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;

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
    public void setFieldsByMapFromDataSource(Map<String, Object> map) {
        Optional.ofNullable(map.getOrDefault(ApplicationLogConstant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.INSERT_TIMESTAMP.getValue(), null))
                .ifPresent(object -> this.setInsertTimestamp(object.toString()));

        Optional.ofNullable(map.getOrDefault(ApplicationLogConstant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.THREAD_NO.getValue(), null))
                .ifPresent(object -> this.setThreadNo(Long.parseLong(object.toString())));

        Optional.ofNullable(map.getOrDefault(ApplicationLogConstant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.ROW_NUMBER.getValue(), null))
                .ifPresent(object -> this.setRowNumber(Integer.parseInt(object.toString())));

        Optional.ofNullable(map.getOrDefault(ApplicationLogConstant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.LOG_TYPE.getValue(), null))
                .ifPresent(object -> this.setLogType(object.toString()));

        Optional.ofNullable(map.getOrDefault(ApplicationLogConstant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.INTERCEPT_POINT.getValue(), null))
                .ifPresent(object -> this.setInterceptPoint(object.toString()));

        Optional.ofNullable(map.getOrDefault(ApplicationLogConstant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.USER_ID.getValue(), null))
                .ifPresent(object -> this.setUserId(object.toString()));

        Optional.ofNullable(map.getOrDefault(ApplicationLogConstant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.SESSION_ID.getValue(), null))
                .ifPresent(object -> this.setSessionId(object.toString()));

        Optional.ofNullable(map.getOrDefault(ApplicationLogConstant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.PROCESS_NAME.getValue(), null))
                .ifPresent(object -> this.setProcessName(object.toString()));

        Optional.ofNullable(map.getOrDefault(ApplicationLogConstant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.PROCESS_RETURN_TYPE.getValue(), null))
                .ifPresent(object -> this.setProcessReturnType(object.toString()));

        Optional.ofNullable(map.getOrDefault(ApplicationLogConstant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.ARGUMENT_VALUE.getValue(), null))
                .ifPresent(object -> this.setArgumentValue(object.toString()));

        Optional.ofNullable(map.getOrDefault(ApplicationLogConstant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.MESSAGE.getValue(), null))
                .ifPresent(object -> this.setMessage(object.toString()));
    }

    @Override
    public void setFieldsByMapFromApi(Map<String, Object> map) {
        Optional.ofNullable(map.getOrDefault(ApplicationLogConstant.API_FIELD_NAME_APPLICATION_LOG.INSERT_TIMESTAMP.getValue(), null))
                .ifPresent(object -> this.setInsertTimestamp(object.toString()));

        Optional.ofNullable(map.getOrDefault(ApplicationLogConstant.API_FIELD_NAME_APPLICATION_LOG.THREAD_NO.getValue(), null))
                .ifPresent(object -> this.setThreadNo(Long.parseLong(object.toString())));

        Optional.ofNullable(map.getOrDefault(ApplicationLogConstant.API_FIELD_NAME_APPLICATION_LOG.ROW_NUMBER.getValue(), null))
                .ifPresent(object -> this.setRowNumber(Integer.parseInt(object.toString())));

        Optional.ofNullable(map.getOrDefault(ApplicationLogConstant.API_FIELD_NAME_APPLICATION_LOG.LOG_TYPE.getValue(), null))
                .ifPresent(object -> this.setLogType(object.toString()));

        Optional.ofNullable(map.getOrDefault(ApplicationLogConstant.API_FIELD_NAME_APPLICATION_LOG.INTERCEPT_POINT.getValue(), null))
                .ifPresent(object -> this.setInterceptPoint(object.toString()));

        Optional.ofNullable(map.getOrDefault(ApplicationLogConstant.API_FIELD_NAME_APPLICATION_LOG.USER_ID.getValue(), null))
                .ifPresent(object -> this.setUserId(object.toString()));

        Optional.ofNullable(map.getOrDefault(ApplicationLogConstant.API_FIELD_NAME_APPLICATION_LOG.SESSION_ID.getValue(), null))
                .ifPresent(object -> this.setSessionId(object.toString()));

        Optional.ofNullable(map.getOrDefault(ApplicationLogConstant.API_FIELD_NAME_APPLICATION_LOG.PROCESS_NAME.getValue(), null))
                .ifPresent(object -> this.setProcessName(object.toString()));

        Optional.ofNullable(map.getOrDefault(ApplicationLogConstant.API_FIELD_NAME_APPLICATION_LOG.PROCESS_RETURN_TYPE.getValue(), null))
                .ifPresent(object -> this.setProcessReturnType(object.toString()));

        Optional.ofNullable(map.getOrDefault(ApplicationLogConstant.API_FIELD_NAME_APPLICATION_LOG.ARGUMENT_VALUE.getValue(), null))
                .ifPresent(object -> this.setArgumentValue(object.toString()));

        Optional.ofNullable(map.getOrDefault(ApplicationLogConstant.API_FIELD_NAME_APPLICATION_LOG.MESSAGE.getValue(), null))
                .ifPresent(object -> this.setMessage(object.toString()));
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
}
