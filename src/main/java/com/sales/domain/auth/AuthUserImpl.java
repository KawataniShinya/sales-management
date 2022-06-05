package com.sales.domain.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Scope("prototype")
public class AuthUserImpl implements AuthUser {

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
    private Date expirationStart;

    @Getter
    @Setter
    private Date expirationEnd;

    @Getter
    @Setter
    private Boolean enabled;

    @Getter
    @Setter
    private Timestamp insertTimestamp;

    @Getter
    @Setter
    private String insertUser;

    @Getter
    @Setter
    private Timestamp updateTimestamp;

    @Getter
    @Setter
    private String updateUser;

    private final AuthUserRepository authUserRepository;

    @Autowired
    public AuthUserImpl(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public AuthUser createAuthUser() {
        return new AuthUserImpl(this.authUserRepository);
    }

    @Override
    public AuthUser setAuthUserByUserId(String username) {

        this.setUserId(username);
        List<Map<String, Object>> resultList = authUserRepository.findEnableUserByUserId(this);
        if (resultList.size() != 1) {
            throw new UsernameNotFoundException("not found : " + username);
        }
        this.setFieldsByMapFromDataSource(resultList.get(0));

        return this;
    }

    @Override
    public void setFieldsByMapFromDataSource(Map<String, Object> map) {
        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.USER_ID.getValue(), null))
                .ifPresent(object -> this.setUserId(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.PASSWORD.getValue(), null))
                .ifPresent(object -> this.setPassword(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.AUTHORITY.getValue(), null))
                .ifPresent(object -> this.setAuthority(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.AUTHORITY_VALUE.getValue(), null))
                .ifPresent(object -> this.setAuthorityValue(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.EXPIRATION_START.getValue(), null))
                .ifPresent(object -> this.setExpirationStart((Date) object));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.EXPIRATION_END.getValue(), null))
                .ifPresent(object -> this.setExpirationEnd((Date) object));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.ENABLED.getValue(), null))
                .ifPresent(object -> this.setEnabled((Boolean) object));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.INSERT_TIMESTAMP.getValue(), null))
                .ifPresent(object -> this.setInsertTimestamp((Timestamp) object));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.INSERT_USER.getValue(), null))
                .ifPresent(object -> this.setInsertUser(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.UPDATE_TIMESTAMP.getValue(), null))
                .ifPresent(object -> this.setUpdateTimestamp((Timestamp) object));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_AUTH_USER.UPDATE_USER.getValue(), null))
                .ifPresent(object -> this.setUpdateUser(object.toString()));
    }
}
