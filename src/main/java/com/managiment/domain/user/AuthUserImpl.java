package com.managiment.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthUserImpl implements AuthUser {
    @Autowired
    private AuthUserRepository authUserRepository;

    @Override
    public List<Map<String, Object>> getAuthUser(String username) {
        return authUserRepository.findByUserId(username);
    }
}
