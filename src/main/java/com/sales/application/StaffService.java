package com.sales.application;

import com.sales.application.bean.StaffServiceBean;

import java.util.Map;

public interface StaffService {
    StaffServiceBean findStaffs(Map<String, Object> map);
}
