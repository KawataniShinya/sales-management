package com.sales.domain.logging;

public class ArgumentValue {

    private String value = "";

    private final int digits = 128;

    public void setValue(String value, boolean forceSetValue) {
        if (value.length() > this.digits) {
            if (forceSetValue) {
                this.value = value.substring(0, this.digits);
            } else {
                // exception
            }
        } else {
            this.value = value;
        }
    }

    public String getValue() {
        return this.value;
    }
}
