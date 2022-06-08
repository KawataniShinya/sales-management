package com.sales.presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuInitResponse {
    private String userId;
    private String userName;
    private String role;
    private String departmentName;
    private String privateEmail;
    private String workplaceEmail;
}
