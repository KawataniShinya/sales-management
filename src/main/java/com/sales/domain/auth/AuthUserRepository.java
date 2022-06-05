package com.sales.domain.auth;

import java.util.List;
import java.util.Map;

public interface AuthUserRepository {
    List<Map<String, Object>> findEnableUserByUserId(AuthUser authUser);
}
