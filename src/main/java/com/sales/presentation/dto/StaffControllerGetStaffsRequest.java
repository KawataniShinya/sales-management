package com.sales.presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffControllerGetStaffsRequest {
    private int limitSize;
    private int page;
}
