package com.sales.domain.logging;

import java.sql.Timestamp;
import java.util.Map;

public interface ApplicationLog{
    ApplicationLog createApplicationLog();
    void init();

    void setInsertTimestamp(String timestamp);
    Timestamp getInsertTimestamp();
    void setThreadNo(long threadNo);
    long getThreadNo();
    void setRowNumber(int rowNumber);
    int getRowNumber();
    void setLogType(String logType);
    String getLogType();
    void setInterceptPoint(String interceptPoint);
    String getInterceptPoint();
    void setUserId(String userId);
    String getUserId();
    void setSessionId(String sessionId);
    String getSessionId();
    void setProcessName(String processName);
    String getProcessName();
    void setProcessReturnType(String processReturnType);
    String getProcessReturnType();
    void setArgumentValue(String argumentValue);
    String getArgumentValue();
    void setMessage(String message);
    String getMessage();

    void outputLog();
    void setFieldsByMapFromDataSource(Map<String, Object> map);
    void setFieldsByMapFromApi(Map<String, Object> map);
}
