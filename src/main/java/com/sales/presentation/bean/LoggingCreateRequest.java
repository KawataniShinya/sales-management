package com.sales.presentation.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoggingCreateRequest {
    public String insertTimestamp;
    public String threadNo;
    public String rowNumber;
    public String logType;
    public String interceptPoint;
    public String userId;
    public String sessionId;
    public String processName;
    public String processReturnType;
    public String argumentValue;
    public String message;
}
