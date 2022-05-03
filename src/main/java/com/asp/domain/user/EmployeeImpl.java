package com.asp.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeImpl implements Employee{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void outputEmployeeAll() {
        System.out.println("Employee_all : " + this.employeeRepository.selectAll());
    }

    @Override
    public void outputEmployeeLimit() {
        System.out.println("Employee_limit : " + this.employeeRepository.selectLimit());
    }
}
