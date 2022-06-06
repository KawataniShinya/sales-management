package com.sales.domain.department;

public class Constant {
    public enum DATA_SOURCE_FIELD_NAME_DEPARTMENT {
        DEPARTMENT_CD("DEPARTMENT_CD"),
        DEPARTMENT_NAME_EN("DEPARTMENT_NAME_EN"),
        DEPARTMENT_NAME_JA("DEPARTMENT_NAME_JA"),
        EXPIRATION_START("EXPIRATION_START"),
        EXPIRATION_END("EXPIRATION_END"),
        EXPIRATION_DATE("EXPIRATION_DATE"), // Expansion
        INSERT_TIMESTAMP("INSERT_TIMESTAMP"),
        INSERT_USER("INSERT_USER"),
        UPDATE_TIMESTAMP("UPDATE_TIMESTAMP"),
        UPDATE_USER("UPDATE_USER");

        private final String value;
        DATA_SOURCE_FIELD_NAME_DEPARTMENT(String value) {this.value = value;}
        public String getValue() {
            return this.value;
        }
    }

    public enum API_FIELD_NAME_DEPARTMENT {
        DEPARTMENT_CD("departmentCd"),
        DEPARTMENT_NAME_EN("departmentNameEn"),
        DEPARTMENT_NAME_JA("departmentNameJa"),
        EXPIRATION_START("expirationStart"),
        EXPIRATION_END("expirationEnd"),
        EXPIRATION_DATE("expirationDate"), // Expansion
        INSERT_TIMESTAMP("insertTimestamp"),
        INSERT_USER("insertUser"),
        UPDATE_TIMESTAMP("updateTimestamp"),
        UPDATE_USER("updateUser");

        private final String value;
        API_FIELD_NAME_DEPARTMENT(String value) {this.value = value;}
        public String getValue() {
            return this.value;
        }
    }
}
