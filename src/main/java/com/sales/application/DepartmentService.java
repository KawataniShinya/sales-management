package com.sales.application;

import com.sales.domain.department.Department;

import java.util.Map;

public interface DepartmentService {
    Department getDepartmentByCd(Map<String, Object> map);
}
