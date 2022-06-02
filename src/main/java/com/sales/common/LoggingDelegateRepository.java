package com.sales.common;

import java.lang.reflect.Method;
import java.util.Map;

public interface LoggingDelegateRepository {
    void loggingDbDebugPoint(
            Class enclosingClass,
            Method enclosingMethod,
            String sqlId,
            String sqlStatement,
            Map<String, Object> paramMap);
}
