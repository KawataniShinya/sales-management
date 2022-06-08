package com.sales.presentation;

import com.sales.common.SessionConstant;
import com.sales.presentation.dto.CommonDisplayHeader;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class CommonDisplay {
    public static void setHeaderParameter(HttpServletRequest request, Model model) {
        CommonDisplayHeader commonDisplayHeader = new CommonDisplayHeader();
        Optional.ofNullable(request.getSession().getAttribute(SessionConstant.ATTRIBUTE.USER_ID.getValue()))
                .ifPresent(object -> commonDisplayHeader.setUserId(object.toString()));
        Optional.ofNullable(request.getSession().getAttribute(SessionConstant.ATTRIBUTE.USER_NAME.getValue()))
                .ifPresent(object -> commonDisplayHeader.setUserName(object.toString()));
        Optional.ofNullable(request.getSession().getAttribute(SessionConstant.ATTRIBUTE.ROLE.getValue()))
                .ifPresent(object -> commonDisplayHeader.setRole(object.toString()));
        Optional.ofNullable(request.getSession().getAttribute(SessionConstant.ATTRIBUTE.DEPARTMENT_NAME.getValue()))
                .ifPresent(object -> commonDisplayHeader.setDepartmentName(object.toString()));
        Optional.ofNullable(request.getSession().getAttribute(SessionConstant.ATTRIBUTE.PRIVATE_EMAIL.getValue()))
                .ifPresent(object -> commonDisplayHeader.setPrivateEmail(object.toString()));
        Optional.ofNullable(request.getSession().getAttribute(SessionConstant.ATTRIBUTE.WORKPLACE_EMAIL.getValue()))
                .ifPresent(object -> commonDisplayHeader.setWorkplaceEmail(object.toString()));
        model.addAttribute("header", commonDisplayHeader);
    }
}
