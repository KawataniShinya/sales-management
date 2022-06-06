package com.sales.presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentGetResponse {
    private String departmentCd;
    private String departmentNameEn;
    private String departmentNameJa;
    private String expirationStart;
    private String expirationEnd;
    private String expirationDate;	// Expansion
    private String insertTimestamp;
    private String insertUser;
    private String updateTimestamp;
    private String updateUser;
}
