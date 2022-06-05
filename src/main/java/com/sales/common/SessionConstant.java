package com.sales.common;

public class SessionConstant {
    public enum ATTRIBUTE {
        SECURITY_CONTEXT("SPRING_SECURITY_CONTEXT"),
        USER_ID("userId"),
        USER_NAME("userName"),
        DEPARTMENT_NAME("departmentName"),
        ROLE("role"),
        PRIVATE_EMAIL("privateEmail"),
        WORKPLACE_EMAIL("WORKPLACEeMAIL");

        private final String value;
        ATTRIBUTE(String value) {
            this.value = value;
        }
        public String getValue() {
            return this.value;
        }
    }
}
