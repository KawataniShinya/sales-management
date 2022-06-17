package com.sales.domain.department;

import java.util.List;

public interface DepartmentDomainService {
    void setDepartments(List<Department> departments);
    List<Department> getDepartments();

    DepartmentDomainService createDepartmentDomainService();
    void init();

    DepartmentDomainService findAllDepartment();
}
