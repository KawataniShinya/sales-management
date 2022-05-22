package com.sales.common.logging;

import java.sql.Timestamp;
import java.util.Map;

public interface ApplicationLog{
    public ApplicationLog createApplicationLog();
    public void init();

    public void setInsertTimestamp(String timestamp);
    public Timestamp getInsertTimestamp();
    public void setThreadNo(long threadNo);
    public long getThreadNo();
    public void setRowNumber(int rowNumber);
    public int getRowNumber();
    public void setLogType(String logType);
    public String getLogType();
    public void setInterceptPoint(String interceptPoint);
    public String getInterceptPoint();
    public void setUserId(String userId);
    public String getUserId();
    public void setSessionId(String sessionId);
    public String getSessionId();
    public void setProcessName(String processName);
    public String getProcessName();
    public void setProcessReturnType(String processReturnType);
    public String getProcessReturnType();
    public void setArgumentValue(String argumentValue);
    public String getArgumentValue();
    public void setMessage(String message);
    public String getMessage();

    public void outputLog();
    public void setFieldsFromMap(Map<String, Object> map);

    public ApplicationLogImpl clone();
}
