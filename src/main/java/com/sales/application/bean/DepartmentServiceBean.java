package com.sales.application.bean;

import com.sales.domain.department.Department;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DepartmentServiceBean {
    private Department department;
    private List<Department> departments;
}
