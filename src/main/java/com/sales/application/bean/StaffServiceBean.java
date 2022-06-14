package com.sales.application.bean;

import com.sales.domain.staff.Staff;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StaffServiceBean {
    private long limitSize;
    private long page;
    private List<Staff> staffs;
}
