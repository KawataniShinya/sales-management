package com.sales.domain.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Scope("prototype")
public class AuthUserImpl implements AuthUser {

    private AuthUserRepository authUserRepository;

    @Getter
    @Setter
    private String id;

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
    private String insertUser;

    @Getter
    @Setter
    private String updateTimestamp;

    @Getter
    @Setter
    private String updateUser;

    @Autowired
    public AuthUserImpl(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public AuthUser setAuthUserByUserId(String username) {

        this.setId(username);
        List<Map<String, Object>> resultList = authUserRepository.findByUserId(this);
        if (resultList.isEmpty() || resultList.size() > 1) {
            throw new UsernameNotFoundException("not found : " + username);
        }
        this.setFieldsFromMap(resultList.get(0));

        return this;
    }

    @Override
    public void setFieldsFromMap(Map<String, Object> map) {
        if(map.containsKey("ID")) this.setId(map.get("ID").toString());
        if(map.containsKey("PASSWORD")) this.setPassword(map.get("PASSWORD").toString());
        if(map.containsKey("AUTHORITY")) this.setAuthority(map.get("AUTHORITY").toString());
        if(map.containsKey("AUTHORITY_VALUE")) this.setAuthorityValue(map.get("AUTHORITY_VALUE").toString());
        if(map.containsKey("EXPIRATION_START")) this.setExpirationStart(map.get("EXPIRATION_START").toString());
        if(map.containsKey("EXPIRATION_END")) this.setExpirationEnd(map.get("EXPIRATION_END").toString());
        if(map.containsKey("ENABLED")) this.setEnabled(map.get("ENABLED").toString());
        if(map.containsKey("INSERT_TIMESTAMP")) this.setInsertTimestamp(map.get("INSERT_TIMESTAMP").toString());
        if(map.containsKey("INSERT_USER")) this.setInsertUser(map.get("INSERT_USER").toString());
        if(map.containsKey("UPDATE_TIMESTAMP")) this.setUpdateTimestamp(map.get("UPDATE_TIMESTAMP").toString());
        if(map.containsKey("UPDATE_USER")) this.setUpdateUser(map.get("UPDATE_USER ").toString());
    }

}
