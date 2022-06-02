package com.sales.presentation.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoggingCreateRequest {
    private String insertTimestamp;
    private String threadNo;
    private String rowNumber;
    private String logType;
    private String interceptPoint;
    private String userId;
    private String sessionId;
    private String processName;
    private String processReturnType;
    private String argumentValue;
    private String message;
}
