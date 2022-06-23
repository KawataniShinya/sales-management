package com.sales.domain.staff;

import java.util.List;
import java.util.Map;

public interface StaffRepository {
    List<Map<String, Object>> findUserByUserIdInExpiration(Staff staff);
    long countUser(StaffDomainService staffDomainService);
    List<Map<String, Object>> findUser(StaffDomainService staffDomainService);
    long countUserNewer(StaffDomainService staffDomainService);
    void insertStaff(Staff staff);
}
