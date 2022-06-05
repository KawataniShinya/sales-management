package com.sales.application;

import com.sales.common.SessionConstant;
import com.sales.domain.staff.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@Scope("prototype")
@Transactional(transactionManager = "applTransactionManager")
public class AuthenticatedServiceImpl implements AuthenticatedService{
    private final Staff staff;

    @Autowired
    public AuthenticatedServiceImpl(Staff staff) {
        this.staff = staff;
    }

    @Override
    public void setSessionAttribute(HttpSession session) {
        Staff staff = this.staff.createStaff();
        staff.setStaffByUserId((String) session.getAttribute(SessionConstant.ATTRIBUTE.USER_ID.getValue()));

        session.setAttribute(SessionConstant.ATTRIBUTE.USER_NAME.getValue(), staff.getFamilyName() + " " + staff.getFirstName());
        session.setAttribute(SessionConstant.ATTRIBUTE.DEPARTMENT_NAME.getValue(), staff.getDepartmentName());
        session.setAttribute(SessionConstant.ATTRIBUTE.PRIVATE_EMAIL.getValue(), staff.getPrivateEmail());
        session.setAttribute(SessionConstant.ATTRIBUTE.WORKPLACE_EMAIL.getValue(), staff.getWorkplaceEmail());
    }
}
