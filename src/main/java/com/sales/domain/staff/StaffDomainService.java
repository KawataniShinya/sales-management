package com.sales.domain.staff;

import java.util.List;

public interface StaffDomainService {
    void setCount(long count);
    long getCount();
    void setLimitSize(int limitSize);
    int getLimitSize();
    void setOffsetSize(int offsetSize);
    int getOffsetSize();
    void setStaffs(List<Staff> staffs);
    List<Staff> getStaffs();

    StaffDomainService createStaffService();

    void init();

    StaffDomainService findAllUser();
}
