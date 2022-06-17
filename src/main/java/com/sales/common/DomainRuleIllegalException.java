package com.sales.common;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class DomainRuleIllegalException extends Exception{
    @Getter
    List<String> messages = new ArrayList<>();

    public DomainRuleIllegalException(String message) {
        super(message);
        this.messages.add(message);
    }

    public DomainRuleIllegalException(List<String> messages) {
        super(messages.toString());
        this.messages.addAll(messages);
    }
}
