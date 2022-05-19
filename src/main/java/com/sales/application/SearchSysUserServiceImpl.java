package com.sales.application;

import com.sales.domain.test.AccessTestSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class SearchSysUserServiceImpl implements SearchSysUserService {

    private AccessTestSys accessTestSys;

    @Autowired
    public SearchSysUserServiceImpl(AccessTestSys accessTestSys) {
        this.accessTestSys = accessTestSys;
    }

    @Override
    public void searchSysUser() {
        this.accessTestSys.outputTestSysAll();
        this.accessTestSys.outputTestSysLimit();
    }
}
