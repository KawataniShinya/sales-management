package com.sales.presentation.dto;

import com.sales.domain.staff.Staff;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StaffControllerGetStaffsResponse {
    private long count;
    private long limitSize;
    private long page;
    private List<Staff> staffs;
}
