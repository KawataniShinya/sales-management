package com.managiment.application;

import com.managiment.domain.user.AccessTestSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchSysUserServiceImpl implements SearchSysUserService {
    @Autowired
    private AccessTestSys accessTestSys;

    @Override
    public void searchSysUser() {
        this.accessTestSys.outputTestSysAll();
        this.accessTestSys.outputTestSysLimit();
    }
}
