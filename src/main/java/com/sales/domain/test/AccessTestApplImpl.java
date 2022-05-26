package com.sales.domain.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class AccessTestApplImpl implements AccessTestAppl {

    private AccessTestApplRepository accessTestApplRepository;

    @Autowired
    public AccessTestApplImpl(AccessTestApplRepository accessTestApplRepository) {
        this.accessTestApplRepository = accessTestApplRepository;
    }

    @Override
    public void outputTestApplAll() {
        System.out.println("AccessTest_Appl_all : " + this.accessTestApplRepository.selectAll());
    }

    @Override
    public void outputTestApplLimit() {
        System.out.println("AccessTest_Appl_limit : " + this.accessTestApplRepository.selectLimit());
    }

    @Override
    public void insertTestApp() {
        System.out.println("AccessTest_Appl_insert");
        this.accessTestApplRepository.insertUser();
    }

}
