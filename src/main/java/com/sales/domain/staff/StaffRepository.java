package com.sales.domain.staff;

import java.util.List;
import java.util.Map;

public interface StaffRepository {
    List<Map<String, Object>> findEnableUserByUserId(Staff staff);
}