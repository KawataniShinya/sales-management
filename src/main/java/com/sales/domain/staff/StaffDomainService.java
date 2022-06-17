package com.sales.domain.staff;

import java.util.Date;
import java.util.List;

public interface StaffDomainService {
    void setCount(long count);
    long getCount();
    void setLimitSize(long limitSize);
    long getLimitSize();
    void setOffsetSize(long offsetSize);
    long getOffsetSize();
    void setPage(long page);
    long getPage();
    void setUserId(String userId);
    String getUserId();
    void setUserName(String userName);
    String getUserName();
    void setDepartmentCd(String departmentCd);
    String getDepartmentCd();
    void setParamExpirationStart(Date paramExpirationStart);
    Date getParamExpirationStart();
    void setParamExpirationEnd(Date expirationEnd);
    Date getParamExpirationEnd();
    void setStaffs(List<Staff> staffs);
    List<Staff> getStaffs();

    StaffDomainService createStaffService();

    void init();

    StaffDomainService findAllUser();
}
