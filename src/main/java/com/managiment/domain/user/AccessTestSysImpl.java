package com.managiment.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessTestSysImpl implements AccessTestSys {
    @Autowired
    private AccessTestSysRepository accessTestSysRepository;

    @Override
    public void outputTestSysAll() {
        System.out.println("AccessTest_Sys_all : " + this.accessTestSysRepository.selectAll());
    }

    @Override
    public void outputTestSysLimit() {
        System.out.println("AccessTest_Sys_limit : " + this.accessTestSysRepository.selectLimit());
    }
}
