package com.sales.domain.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class AccessTestSysImpl implements AccessTestSys {

    private AccessTestSysRepository accessTestSysRepository;

    @Autowired
    public AccessTestSysImpl(AccessTestSysRepository accessTestSysRepository) {
        this.accessTestSysRepository = accessTestSysRepository;
    }

    @Override
    public void outputTestSysAll() {
        System.out.println("AccessTest_Sys_all : " + this.accessTestSysRepository.selectAll());
    }

    @Override
    public void outputTestSysLimit() {
        System.out.println("AccessTest_Sys_limit : " + this.accessTestSysRepository.selectLimit());
    }

    @Override
    public void insertTestSys() {
        System.out.println("AccessTest_Sys_insert");
        this.accessTestSysRepository.insertUser();
    }
}
