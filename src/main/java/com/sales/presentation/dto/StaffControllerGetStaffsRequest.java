package com.sales.presentation.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
public class StaffControllerGetStaffsRequest {
    @Positive
    private int limitSize = 25;

    @Positive
    private int page = 1;

    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String userId;

    private String userName;

    private String departmentCd;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date paramExpirationStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date paramExpirationEnd;

    private String submitType;
}
