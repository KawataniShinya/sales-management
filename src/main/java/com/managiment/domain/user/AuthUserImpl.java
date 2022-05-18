package com.managiment.domain.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthUserImpl implements AuthUser {
    @Autowired
    private AuthUserRepository authUserRepository;

    @Getter
    @Setter
    private String userId;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String authority;

    @Getter
    @Setter
    private String authorityValue;

    @Getter
    @Setter
    private String expirationStart;

    @Getter
    @Setter
    private String expirationEnd;

    @Getter
    @Setter
    private String enabled;

    @Getter
    @Setter
    private String insertTimestamp;

    @Getter
    @Setter
    private String updateTimestamp;

    @Getter
    @Setter
    private String updateUser;

    @Override
    public List<Map<String, Object>> getAuthUserByUserId(String username) {
        this.setUserId(username);
        return authUserRepository.findByUserId(this);
    }


}
