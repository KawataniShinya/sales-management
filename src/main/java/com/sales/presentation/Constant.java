package com.sales.presentation;

public class Constant {
    public enum RESPONSE_COMMON {
        GLOBAL_ERROR_MESSAGES("globalErrorMessages"),
        FIELD_ERROR_MESSAGES("fieldErrorMessages");

        private final String value;
        RESPONSE_COMMON(String value) {this.value = value;}
        public String getValue() {
            return this.value;
        }
    }
}
