package com.sales.application;

import com.sales.application.bean.DepartmentServiceBean;
import com.sales.domain.department.Constant;
import com.sales.domain.department.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Scope("prototype")
@Transactional(transactionManager = "applTransactionManager")
public class DepartmentServiceImpl implements DepartmentService{
    private final Department department;

    @Autowired
    public DepartmentServiceImpl(Department department) {
        this.department = department;
    }

    @Override
    public DepartmentServiceBean getDepartmentByCd(Map<String, Object> map) {
        DepartmentServiceBean departmentServiceBean = new DepartmentServiceBean();
        Department department = this.department.createDepartment();
        department.setDepartmentByCd((String) map.get(Constant.API_FIELD_NAME_DEPARTMENT.DEPARTMENT_CD.getValue()));
        departmentServiceBean.setDepartment(department);
        return departmentServiceBean;
    }
}
