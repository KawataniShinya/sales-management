package com.sales.domain.user;

public class Constant {
    public enum CATEGORY {
        PERSONALITY("0001"),
        GENDER("0002"),
        ROLE("0003");

        private String value;
        private CATEGORY(String value) {
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

        private String value;
        private ROLE(String value) {
            this.value = value;
        }
        public String getValue() {
            return this.value;
        }
    }

    public enum DATA_SOURCE_FIELD_NAME_GENERIC_CD {
        CATEGORY("CATEGORY");

        private String value;
        private DATA_SOURCE_FIELD_NAME_GENERIC_CD(String value) {this.value = value;}
        public String getValue() {
            return this.value;
        }
    }

    public enum DATA_SOURCE_FIELD_NAME_AUTH_USER {
        ID("ID"),
        PASSWORD("PASSWORD"),
        AUTHORITY("AUTHORITY"),
        AUTHORITY_VALUE("AUTHORITY_VALUE"),
        EXPIRATION_START("EXPIRATION_START"),
        EXPIRATION_END("EXPIRATION_END"),
        ENABLED("ENABLED"),
        INSERT_TIMESTAMP("INSERT_TIMESTAMP"),
        INSERT_USER("INSERT_USER"),
        UPDATE_TIMESTAMP("UPDATE_TIMESTAMP"),
        UPDATE_USER("UPDATE_USER");

        private String value;
        private DATA_SOURCE_FIELD_NAME_AUTH_USER(String value) {this.value = value;}
        public String getValue() {
            return this.value;
        }
    }
}
