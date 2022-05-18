package com.managiment.domain.user;

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
        ROLE_USER("0001"),
        ROLE_ADMIN("0002");

        private String value;

        private ROLE(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}
