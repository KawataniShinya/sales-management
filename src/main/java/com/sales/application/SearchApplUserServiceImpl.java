package com.sales.application;

import com.sales.domain.test.AccessTestAppl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope("prototype")
public class SearchApplUserServiceImpl implements SearchApplUserService {

    private final AccessTestAppl accessTestAppl;

    @Autowired
    public SearchApplUserServiceImpl(AccessTestAppl accessTestAppl) {
        this.accessTestAppl = accessTestAppl;
    }

    @Transactional(transactionManager = "applTransactionManager")
    @Override
    public void searchApplUser() {
        this.accessTestAppl.insertTestApp();
        this.accessTestAppl.outputTestApplAll();
        this.accessTestAppl.outputTestApplLimit();
    }
}
