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

    public enum RESPONSE_FORM_STATE {
        FORM_STATE("formState"),
        FORM_STATE_ADD_INIT("stateAddInit"),
        FORM_STATE_ADD_CONFIRM("stateAddConfirm"),
        FORM_STATE_ADD_EXECUTE("stateAddExecute");

        private final String value;
        RESPONSE_FORM_STATE(String value) {this.value = value;}
        public String getValue() {
            return this.value;
        }
    }

    public enum REQUEST_SUBMIT_TYPE {
        SUBMIT_TYPE("submitType"),
        SUBMIT_TYPE_ADD_CONFIRM("submitAddConfirm"),
        SUBMIT_TYPE_ADD_CANCEL("submitAddCancel"),
        SUBMIT_TYPE_ADD_EXECUTE("submitAddExecute");

        private final String value;
        REQUEST_SUBMIT_TYPE(String value) {this.value = value;}
        public String getValue() {
            return this.value;
        }
    }
}
