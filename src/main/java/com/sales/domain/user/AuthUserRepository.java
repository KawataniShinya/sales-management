package com.sales.domain.user;

import java.util.List;
import java.util.Map;

public interface AuthUserRepository {
    List<Map<String, Object>> findEnableUserByUserId(AuthUser authUser);
}
