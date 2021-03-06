package com.sales.common;

public class ApplicationLogConstant {
    public enum DATA_SOURCE_FIELD_NAME_APPLICATION_LOG {
        INSERT_TIMESTAMP("INSERT_TIMESTAMP"),
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

        private final String value;
        DATA_SOURCE_FIELD_NAME_APPLICATION_LOG(String value) {this.value = value;}
        public String getValue() {
            return this.value;
        }
    }

    public enum API_FIELD_NAME_APPLICATION_LOG {
        INSERT_TIMESTAMP("insertTimestamp"),
        THREAD_NO("threadNo"),
        ROW_NUMBER("rowNumber"),
        LOG_TYPE("logType"),
        INTERCEPT_POINT("interceptPoint"),
        USER_ID("userId"),
        SESSION_ID("sessionId"),
        PROCESS_NAME("processName"),
        PROCESS_RETURN_TYPE("processReturnType"),
        ARGUMENT_VALUE("argumentValue"),
        MESSAGE("message");

        private final String value;
        API_FIELD_NAME_APPLICATION_LOG(String value) {this.value = value;}
        public String getValue() {
            return this.value;
        }
    }

    public enum LOG_TYPE {
        ACCESS("ACCESS"),
        ASPECT("ASPECT"),
        DEBUG("DEBUG");

        private final String value;
        LOG_TYPE(String value) {this.value = value;}
        public String getValue() {
            return this.value;
        }
    }

    public enum INTERCEPT_POINT {
        PRE_HANDLE("PreHandle"),
        POST_HANDLE("PostHandle"),
        AFTER_COMPLETION("AfterCompletion"),
        PRE_SERVICE("PreService"),
        POST_SERVICE("PostService"),
        DOMAIN("Domain"),
        REPOSITORY("Repository");

        private final String value;
        INTERCEPT_POINT(String value) {this.value = value;}
        public String getValue() {
            return this.value;
        }
    }
}
