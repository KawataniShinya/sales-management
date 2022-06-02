package com.sales.infrastructure;

import com.sales.common.ApplicationLogConstant;
import com.sales.common.LoggingDelegateRepository;
import com.sales.common.ThreadVariables;
import com.sales.presentation.LoggingController;
import com.sales.presentation.dto.LoggingCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@Repository
@Scope("prototype")
public class LoggingDelegateRepositoryImpl implements LoggingDelegateRepository {
    private final LoggingController loggingController;

    @Autowired
    public LoggingDelegateRepositoryImpl(LoggingController loggingController) {
        this.loggingController = loggingController;
    }

    @Override
    public void loggingDbDebugPoint(
            Class enclosingClass,
            Method enclosingMethod,
            String sqlId,
            String sqlStatement,
            Map<String, Object> paramMap) {
        StringBuilder sbParameterType = new StringBuilder();
        Arrays.stream(enclosingMethod.getParameterTypes()).forEach(parametertype -> {
            if (!sbParameterType.isEmpty()) {
                sbParameterType.append(", ");
            }
            sbParameterType.append(parametertype.getName());
        });

        LoggingCreateRequest loggingCreateRequest = new LoggingCreateRequest();

        loggingCreateRequest.setThreadNo(String.valueOf(Thread.currentThread().getId()));
        loggingCreateRequest.setRowNumber(String.valueOf(ThreadVariables.threadLocal.get().getLogRowNumberInThisThread()));
        loggingCreateRequest.setLogType(ApplicationLogConstant.LOG_TYPE.DEBUG.getValue());
        loggingCreateRequest.setInterceptPoint(ApplicationLogConstant.INTERCEPT_POINT.REPOSITORY.getValue());
        loggingCreateRequest.setUserId(ThreadVariables.threadLocal.get().getUserId());
        loggingCreateRequest.setSessionId(ThreadVariables.threadLocal.get().getSessionId());
        loggingCreateRequest.setProcessName(enclosingClass.getName() + "." + enclosingMethod.getName() + "(" + sbParameterType + ")");
        loggingCreateRequest.setProcessReturnType(enclosingMethod.getReturnType().getName());
        loggingCreateRequest.setArgumentValue("");
        loggingCreateRequest.setMessage(sqlId + " : " + sqlStatement + " / " + paramMap);

        ArrayList<LoggingCreateRequest> loggingArray = new ArrayList<>();
        loggingArray.add(loggingCreateRequest);

        this.loggingController.create(loggingArray);
    }
}
