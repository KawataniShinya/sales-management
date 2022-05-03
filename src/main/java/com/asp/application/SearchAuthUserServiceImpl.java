package com.asp.application;

import com.asp.domain.user.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchAuthUserServiceImpl implements SearchAuthUserService{
    @Autowired
    private AuthUser authUser;

    @Override
    public void searchAuthUser() {
        this.authUser.outputAuthUserAll();
        this.authUser.outputAuthUserLimit();
    }
}
