package com.sales.presentation.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class StaffControllerGetStaffsRequest {
    @Positive
    private int limitSize;
    @Positive
    private int page;
}
