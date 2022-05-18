package com.managiment.domain.user;

import java.util.List;
import java.util.Map;

public interface AuthUser {
    public void setUserId(String userId);
    public String getUserId();
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
    public String getUpdateTimestamp();
    public void setUpdateUser(String updateUser);
    public String getUpdateUser();
    public List<Map<String, Object>> getAuthUserByUserId(String username);
}
