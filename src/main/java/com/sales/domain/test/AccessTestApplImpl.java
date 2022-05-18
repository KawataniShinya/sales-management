package com.sales.domain.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class AccessTestApplImpl implements AccessTestAppl {
    @Autowired
    private AccessTestApplRepository accessTestApplRepository;

    @Override
    public void outputTestApplAll() {
        System.out.println("AccessTest_Appl_all : " + this.accessTestApplRepository.selectAll());
    }

    @Override
    public void outputTestApplLimit() {
        System.out.println("AccessTest_Appl_limit : " + this.accessTestApplRepository.selectLimit());
    }

}
