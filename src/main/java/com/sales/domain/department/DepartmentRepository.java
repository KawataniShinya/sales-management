package com.sales.domain.department;

import java.util.List;
import java.util.Map;

public interface DepartmentRepository {
    List<Map<String, Object>> findByDepartmentCd(Department department);
}
