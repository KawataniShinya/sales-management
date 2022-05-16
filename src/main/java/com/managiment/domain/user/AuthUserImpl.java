package com.managiment.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthUserImpl implements AuthUser{
    @Autowired
    private AuthUserRepository authUserRepository;

    @Override
    public void outputAuthUserAll() {
        System.out.println("AuthUser_all : " + this.authUserRepository.selectAll());
    }

    @Override
    public void outputAuthUserLimit() {
        System.out.println("AuthUser_limit : " + this.authUserRepository.selectLimit());
    }
}
