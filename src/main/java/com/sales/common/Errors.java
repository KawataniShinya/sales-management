package com.sales.common;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Errors {
    private Map<String, String> field = new HashMap<>();
    private List<String> global = new ArrayList<>();
}
