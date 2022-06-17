package com.sales.application;

import com.sales.application.bean.StaffServiceBean;
import com.sales.common.DomainRuleIllegalException;

import java.util.Map;

public interface StaffService {
    StaffServiceBean findStaffs(Map<String, Object> map) throws DomainRuleIllegalException;
}
