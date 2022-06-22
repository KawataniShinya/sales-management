package com.sales.presentation.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.Date;

@Getter
@Setter
public class StaffControllerDetailRequest {

    @Positive
    private int limitSize;

    @Positive
    private int page;

    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String userId;

    private String familyName;

    private String firstName;

    private String departmentCd;

    private String genderCd;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;

    private String bloodTypeCd;

    private String addressPrefectureCd;

    private String addressMunicipality;

    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String privateTelNo;

    @Pattern(regexp = "^[!-~]*$")
    private String privateEmail;

    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String workplaceTelNo;

    @Pattern(regexp = "^[!-~]*$")
    private String workplaceEmail;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expirationStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expirationEnd;

    private String submitType;
}
