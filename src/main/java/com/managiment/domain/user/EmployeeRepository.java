package com.managiment.domain.user;

import java.util.List;
import java.util.Map;

public interface EmployeeRepository {
    public List<Map<String, Object>> selectAll();
    public List<Map<String, Object>> selectLimit();
}
