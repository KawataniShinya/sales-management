package com.sales.domain.department;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Scope("prototype")
public class DepartmentDomainServiceImpl implements DepartmentDomainService{
    @Getter
    @Setter
    List<Department> departments;

    private final Department department;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentDomainServiceImpl(Department department, DepartmentRepository departmentRepository) {
        this.department = department;
        this.departmentRepository = departmentRepository;
        this.init();
    }

    @Override
    public DepartmentDomainService createDepartmentDomainService() {
        return new DepartmentDomainServiceImpl(department, this.departmentRepository);
    }

    @Override
    public void init() {
        this.departments = new ArrayList<>();
    }

    @Override
    public DepartmentDomainService findAllDepartment() {
        List<Map<String, Object>> resultList = this.departmentRepository.findAllDepartmentInExpiration(this);
        resultList.forEach(resultMap -> {
            Department department = this.department.createDepartment();
            department.setFieldsByMapFromDataSource(resultMap);
            this.departments.add(department);
        });
        return this;
    }
}
