package com.sales.domain.logging;

public class Constant {
    public enum DATA_SOURCE_FIELD_NAME_APPLICATION_LOG {
        TIMESTAMP("TIMESTAMP"),
        THREAD_NO("THREAD_NO"),
        ROW_NUMBER("ROW_NUMBER"),
        LOG_TYPE("LOG_TYPE"),
        INTERCEPT_POINT("INTERCEPT_POINT"),
        USER_ID("USER_ID"),
        SESSION_ID("SESSION_ID"),
        PROCESS_NAME("PROCESS_NAME"),
        PROCESS_RETURN_TYPE("PROCESS_RETURN_TYPE"),
        ARGUMENT_VALUE("ARGUMENT_VALUE"),
        MESSAGE("MESSAGE");

        private String value;
        private DATA_SOURCE_FIELD_NAME_APPLICATION_LOG(String value) {this.value = value;}
        public String getValue() {
            return this.value;
        }
    }
}
