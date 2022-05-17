package com.managiment.domain.user;

import java.util.List;
import java.util.Map;

public interface AuthUser {
    public List<Map<String, Object>> getAuthUser(String username);
}
