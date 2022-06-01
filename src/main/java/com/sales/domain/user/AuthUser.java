package com.sales.domain.user;

import java.util.Map;

public interface AuthUser {
    AuthUser createAuthUser();

    void setUserId(String userId);
    String getUserId();
    void setPassword(String password);
    String getPassword();
    void setAuthority(String authority);
    String getAuthority();
    void setAuthorityValue(String authorityValue);
    String getAuthorityValue();
    void setExpirationStart(String expirationStart);
    String getExpirationStart();
    void setExpirationEnd(String expirationEnd);
    String getExpirationEnd();
    void setEnabled(String enabled);
    String getEnabled();
    void setInsertTimestamp(String insertTimestamp);
    String getInsertTimestamp();
    void setInsertUser(String insertUser);
    String getInsertUser();
    void setUpdateUser(String updateUser);
    String getUpdateUser();

    AuthUser setAuthUserByUserId(String username);
    void setFieldsByMapFromDataSource(Map<String, Object> map);
}
