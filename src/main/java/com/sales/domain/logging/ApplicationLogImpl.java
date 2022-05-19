package com.sales.domain.logging;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Scope("prototype")
public class ApplicationLogImpl implements ApplicationLog{

    @Getter
    @Setter
    private String timestamp;

    @Getter
    @Setter
    private int threadNo;

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

    @Getter
    @Setter
    private String processReturnType;

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
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.TIMESTAMP.getValue())) this.setTimestamp(map.get(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.TIMESTAMP.getValue()).toString());
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.THREAD_NO.getValue())) this.setThreadNo(Integer.parseInt(map.get(Constant.DATA_SOURCE_FIELD_NAME_APPLICATION_LOG.THREAD_NO.getValue()).toString()));
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
}
