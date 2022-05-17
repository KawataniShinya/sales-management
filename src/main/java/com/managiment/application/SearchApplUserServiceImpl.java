package com.managiment.application;

import com.managiment.domain.user.AccessTestAppl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchApplUserServiceImpl implements SearchApplUserService {
    @Autowired
    private AccessTestAppl accessTestAppl;

    @Override
    public void searchApplUser() {
        this.accessTestAppl.outputTestApplAll();
        this.accessTestAppl.outputTestApplLimit();
    }
}
