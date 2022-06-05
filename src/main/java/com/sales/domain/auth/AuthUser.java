package com.sales.domain.auth;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

public interface AuthUser {
    AuthUser createAuthUser();

    void setUserId(String userId);
    String getUserId();
    void setPassword(String password);
    String getPassword();
    void setAuthority(String authority);
    String getAuthority();
    void setAuthorityValue(String authorityValue); // Expansion
    String getAuthorityValue(); // Expansion
    void setExpirationStart(Date expirationStart);
    Date getExpirationStart();
    void setExpirationEnd(Date expirationEnd);
    Date getExpirationEnd();
    void setEnabled(Boolean enabled);
    Boolean getEnabled();
    void setInsertTimestamp(Timestamp insertTimestamp);
    Timestamp getInsertTimestamp();
    void setInsertUser(String insertUser);
    String getInsertUser();
    void setUpdateTimestamp(Timestamp updateTimestamp);
    Timestamp getUpdateTimestamp();
    void setUpdateUser(String updateUser);
    String getUpdateUser();

    AuthUser setAuthUserByUserId(String username);

    void setFieldsByMapFromDataSource(Map<String, Object> map);
}
