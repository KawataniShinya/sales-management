package com.sales.application;

import com.sales.application.bean.DepartmentServiceBean;

import java.util.Map;

public interface DepartmentService {
    DepartmentServiceBean getDepartmentByCd(Map<String, Object> map);
    DepartmentServiceBean getAllDepartments();
}
