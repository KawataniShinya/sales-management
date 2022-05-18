package com.sales.domain.user;

import java.util.Map;

public interface AuthUser {
    public void setId(String userId);
    public String getId();
    public void setPassword(String password);
    public String getPassword();
    public void setAuthority(String authority);
    public String getAuthority();
    public void setAuthorityValue(String authorityValue);
    public String getAuthorityValue();
    public void setExpirationStart(String expirationStart);
    public String getExpirationStart();
    public void setExpirationEnd(String expirationEnd);
    public String getExpirationEnd();
    public void setEnabled(String enabled);
    public String getEnabled();
    public void setInsertTimestamp(String insertTimestamp);
    public String getInsertTimestamp();
    public void setInsertUser(String insertUser);
    public String getInsertUser();
    public void setUpdateUser(String updateUser);
    public String getUpdateUser();
    public AuthUser setAuthUserByUserId(String username);
    public void setFieldsFromMap(Map<String, Object> map);
}
