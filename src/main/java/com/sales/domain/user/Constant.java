package com.sales.domain.user;

public class Constant {
    public enum CATEGORY {
        PERSONALITY("0001"),
        GENDER("0002"),
        ROLE("0003");

        private final String value;
        CATEGORY(String value) {
            this.value = value;
        }
        public String getValue() {
            return this.value;
        }
    }

    public enum ROLE {
        ROLE_GUEST("0000"),
        ROLE_USER("0001"),
        ROLE_ADMIN("0002"),
        ROLE_STAFF("0003");

        private final String value;
        ROLE(String value) {
            this.value = value;
        }
        public String getValue() {
            return this.value;
        }
    }

    public enum DATA_SOURCE_FIELD_NAME_GENERIC_CD {
        CATEGORY("CATEGORY");

        private final String value;
        DATA_SOURCE_FIELD_NAME_GENERIC_CD(String value) {this.value = value;}
        public String getValue() {
            return this.value;
        }
    }

    public enum DATA_SOURCE_FIELD_NAME_AUTH_USER {
        USER_ID("USER_ID"),
        PASSWORD("PASSWORD"),
        AUTHORITY("AUTHORITY"),
        AUTHORITY_VALUE("AUTHORITY_VALUE"),
        EXPIRATION_START("EXPIRATION_START"),
        EXPIRATION_END("EXPIRATION_END"),
        ENABLED("ENABLED"),
        INSERT_TIMESTAMP("INSERT_TIMESTAMP"),
        INSERT_USER("INSERT_USER"),
        UPDATE_TIMESTAMP("UPDATE_TIMESTAMP"),
        UPDATE_USER("UPDATE_USER"),
        // Expansion
        EXPIRATION_DATE("EXPIRATION_DATE");

        private final String value;
        DATA_SOURCE_FIELD_NAME_AUTH_USER(String value) {this.value = value;}
        public String getValue() {
            return this.value;
        }
    }
}
