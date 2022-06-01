package com.sales.application;

import com.sales.domain.logging.ApplicationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Scope("prototype")
public class LoggingServiceImpl implements LoggingService{
    private final ApplicationLog applicationLog;

    @Autowired
    public LoggingServiceImpl(ApplicationLog applicationLog) {
        this.applicationLog = applicationLog;
    }

    @Override
    public void outputLog(Map<String, Object> map) {
        this.applicationLog.setFieldsByMapFromApi(map);
        this.applicationLog.outputLog();
    }
}
