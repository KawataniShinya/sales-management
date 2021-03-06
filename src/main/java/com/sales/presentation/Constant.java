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
        FORM_STATE_ADD_EXECUTE("stateAddExecute"),
        FORM_STATE_DELETE_CONFIRM("stateDeleteConfirm"),
        FORM_STATE_DELETE_EXECUTE("stateDeleteExecute"),
        FORM_STATE_UPDATE_INIT("stateUpdateInit"),
        FORM_STATE_UPDATE_CONFIRM("stateUpdateConfirm"),
        FORM_STATE_UPDATE_EXECUTE("stateUpdateExecute");

        private final String value;
        RESPONSE_FORM_STATE(String value) {this.value = value;}
        public String getValue() {
            return this.value;
        }
    }

    public enum REQUEST_SUBMIT_TYPE {
        SUBMIT_TYPE("submitType"),
        SEARCH_EXECUTE("searchExecute"),
        SUBMIT_TYPE_ADD_CONFIRM("submitAddConfirm"),
        SUBMIT_TYPE_ADD_CANCEL("submitAddCancel"),
        SUBMIT_TYPE_ADD_EXECUTE("submitAddExecute"),
        SUBMIT_TYPE_DELETE_CANCEL("submitDeleteCancel"),
        SUBMIT_TYPE_DELETE_EXECUTE("submitDeleteExecute"),
        SUBMIT_TYPE_UPDATE_CONFIRM("submitUpdateConfirm"),
        SUBMIT_TYPE_UPDATE_CANCEL("submitUpdateCancel"),
        SUBMIT_TYPE_UPDATE_EXECUTE("submitUpdateExecute");

        private final String value;
        REQUEST_SUBMIT_TYPE(String value) {this.value = value;}
        public String getValue() {
            return this.value;
        }
    }
}
