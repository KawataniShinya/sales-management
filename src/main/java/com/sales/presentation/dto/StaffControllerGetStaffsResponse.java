package com.sales.presentation.dto;

import com.sales.domain.staff.Staff;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StaffControllerGetStaffsResponse {
    List<Staff> staffs;
}
