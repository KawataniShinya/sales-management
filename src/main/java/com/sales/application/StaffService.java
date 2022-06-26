package com.sales.application;

import com.sales.application.bean.StaffServiceBean;
import com.sales.common.DomainRuleIllegalException;
import com.sales.domain.staff.Staff;

import java.util.Map;

public interface StaffService {
    StaffServiceBean findStaffs(Map<String, Object> map) throws DomainRuleIllegalException;
    void checkAddStaff(Map<String, Object> map) throws DomainRuleIllegalException;
    Staff getStaffByParamWithoutValidation(Map<String, Object> map);
    Staff getStaffByParam(Map<String, Object> map) throws DomainRuleIllegalException;
    void addStaff(Map<String, Object> map) throws DomainRuleIllegalException;
    void checkDeleteStaff(Map<String, Object> map) throws DomainRuleIllegalException;
    void deleteStaff(Map<String, Object> map) throws DomainRuleIllegalException;
    void checkUpdateStaff(Map<String, Object> map) throws DomainRuleIllegalException;
    void updateStaff(Map<String, Object> map) throws DomainRuleIllegalException;
}
