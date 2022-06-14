package com.sales.domain.staff;

import java.util.List;

public interface StaffDomainService {
    void setCount(long count);
    long getCount();
    void setLimitSize(long limitSize);
    long getLimitSize();
    void setPage(long page);
    long getPage();
    void setOffsetSize(long offsetSize);
    long getOffsetSize();
    void setStaffs(List<Staff> staffs);
    List<Staff> getStaffs();

    StaffDomainService createStaffService();

    void init();

    StaffDomainService findAllUser();
}
