package com.sales.common;

public class SessionConstant {
    public enum ATTRIBUTE {
        SECURITY_CONTEXT("SPRING_SECURITY_CONTEXT"),
        USER_ID("userId"),
        USER_NAME("userName"),
        ROLE("role");

        private final String value;
        ATTRIBUTE(String value) {
            this.value = value;
        }
        public String getValue() {
            return this.value;
        }
    }
}
