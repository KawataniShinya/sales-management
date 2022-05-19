package com.sales.application;

import com.sales.domain.test.AccessTestAppl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class SearchApplUserServiceImpl implements SearchApplUserService {

    private AccessTestAppl accessTestAppl;

    @Autowired
    public SearchApplUserServiceImpl(AccessTestAppl accessTestAppl) {
        this.accessTestAppl = accessTestAppl;
    }

    @Override
    public void searchApplUser() {
        this.accessTestAppl.outputTestApplAll();
        this.accessTestAppl.outputTestApplLimit();
    }
}
