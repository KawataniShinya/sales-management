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

    private AuthUserRepository authUserRepository;

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
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.ID.getValue())) this.setId(map.get(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.ID.getValue()).toString());
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.PASSWORD.getValue())) this.setPassword(map.get(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.PASSWORD.getValue()).toString());
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.AUTHORITY.getValue())) this.setAuthority(map.get(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.AUTHORITY.getValue()).toString());
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.AUTHORITY_VALUE.getValue())) this.setAuthorityValue(map.get(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.AUTHORITY_VALUE.getValue()).toString());
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.EXPIRATION_START.getValue())) this.setExpirationStart(map.get(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.EXPIRATION_START.getValue()).toString());
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.EXPIRATION_END.getValue())) this.setExpirationEnd(map.get(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.EXPIRATION_END.getValue()).toString());
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.ENABLED.getValue())) this.setEnabled(map.get(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.ENABLED.getValue()).toString());
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.INSERT_TIMESTAMP.getValue())) this.setInsertTimestamp(map.get(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.INSERT_TIMESTAMP.getValue()).toString());
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.INSERT_USER.getValue())) this.setInsertUser(map.get(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.INSERT_USER.getValue()).toString());
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.UPDATE_TIMESTAMP.getValue())) this.setUpdateTimestamp(map.get(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.UPDATE_TIMESTAMP.getValue()).toString());
        if(map.containsKey(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.UPDATE_USER.getValue())) this.setUpdateUser(map.get(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.UPDATE_USER.getValue()).toString());
    }

}
