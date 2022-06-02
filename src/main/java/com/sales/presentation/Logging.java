package com.sales.presentation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sales.application.LoggingService;
import com.sales.presentation.bean.LoggingCreateRequest;
import com.sales.presentation.bean.LoggingCreateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Scope("prototype")
public class Logging {
    public final LoggingService loggingService;

    @Autowired
    public Logging(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @RequestMapping(value = "/logging", method = RequestMethod.POST)
    public List<LoggingCreateResponse> create(@RequestBody List<LoggingCreateRequest> listLoggingCreateRequest) {
        listLoggingCreateRequest.forEach(loggingCreateRequest ->
                this.loggingService.outputLog(new ObjectMapper().convertValue(loggingCreateRequest, new TypeReference<>() { })));
        return null;
    }
}
