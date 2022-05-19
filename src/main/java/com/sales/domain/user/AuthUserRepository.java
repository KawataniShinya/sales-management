package com.sales.domain.user;

import java.util.List;
import java.util.Map;

public interface AuthUserRepository {
    public List<Map<String, Object>> findByUserId(AuthUser authUser);
}