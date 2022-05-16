package com.managiment.application;

import com.managiment.domain.user.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchEmployeeServiceImpl implements SearchEmployeeService{
    @Autowired
    private Employee employee;

    @Override
    public void searchEmployee() {
        this.employee.outputEmployeeAll();
        this.employee.outputEmployeeLimit();
    }
}
