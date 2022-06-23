package com.sales.application;

import com.sales.application.bean.StaffServiceBean;
import com.sales.common.DomainRuleIllegalException;
import com.sales.domain.staff.Constant;
import com.sales.domain.staff.Staff;
import com.sales.domain.staff.StaffDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Scope("prototype")
public class StaffServiceImpl implements StaffService{
    private final StaffDomainService staffDomainService;
    private final Staff staff;

    @Autowired
    public StaffServiceImpl(StaffDomainService staffDomainService, Staff staff) {
        this.staffDomainService = staffDomainService;
        this.staff = staff;
    }

    @Override
    public StaffServiceBean findStaffs(Map<String, Object> map) throws DomainRuleIllegalException {
        final List<String> errorMessages = new ArrayList<>();
        StaffDomainService staffDomainService = this.staffDomainService.createStaffService();

        setFindParameterOrSetErrorMessages(map, errorMessages, staffDomainService);
        if (!errorMessages.isEmpty()) {
            throw new DomainRuleIllegalException(errorMessages);
        }

        List<Staff> staffs = staffDomainService.findAllUser().getStaffs();

        StaffServiceBean staffServiceBean = new StaffServiceBean();
        setResultToBean(staffDomainService, staffs, staffServiceBean);

        return staffServiceBean;
    }

    @Override
    public void checkAddStaff(Map<String, Object> map) throws DomainRuleIllegalException {
        List<String> errorMessages = new ArrayList<>();

        StaffDomainService staffDomainService = this.staffDomainService.createStaffService();
        staffDomainService.setUserId((String) map.get(Constant.API_FIELD_NAME_STAFF.USER_ID.getValue()));
        staffDomainService.setParamExpirationStart((Date) map.get(Constant.API_FIELD_NAME_STAFF.EXPIRATION_START.getValue()));
        try {
            staffDomainService.checkAddStaff();
        } catch (DomainRuleIllegalException e) {
            errorMessages.addAll(e.getMessages());
        }

        Staff staff = this.staff.createStaff();
        try {
            staff.setFieldsByMapFromApi(map);
        } catch (DomainRuleIllegalException e) {
            errorMessages.addAll(e.getMessages());
        }

        throw new DomainRuleIllegalException(errorMessages);
    }

    @Override
    public Staff getStaffByParamWithoutValidation(Map<String, Object> map) {
        Staff staff = this.staff.createStaff();
        try {
            staff.setFieldsByMapFromApi(map);
        } catch (DomainRuleIllegalException e) {
            e.printStackTrace();
        }
        return staff;
    }

    private void setResultToBean(StaffDomainService staffDomainService, List<Staff> staffs, StaffServiceBean staffServiceBean) {
        staffServiceBean.setStaffs(staffs);
        staffServiceBean.setCount(staffDomainService.getCount());
        staffServiceBean.setLimitSize(staffDomainService.getLimitSize());
        staffServiceBean.setPage(staffDomainService.getPage());
    }

    private void setFindParameterOrSetErrorMessages(Map<String, Object> map, List<String> errorMessages, StaffDomainService staffDomainService) {
        Optional.ofNullable(map.getOrDefault(Constant.API_SEARCH_PARAM_STAFF.LIMIT_SIZE.getValue(), null))
                .ifPresent(object -> staffDomainService.setLimitSize(((Integer) object).longValue()));
        Optional.ofNullable(map.getOrDefault(Constant.API_SEARCH_PARAM_STAFF.PAGE.getValue(), null))
                .ifPresent(object -> staffDomainService.setPage(((Integer) object).longValue()));
        Optional.ofNullable(map.getOrDefault(Constant.API_SEARCH_PARAM_STAFF.USER_ID.getValue(), null))
                .ifPresent(object -> staffDomainService.setUserId((String) object));
        Optional.ofNullable(map.getOrDefault(Constant.API_SEARCH_PARAM_STAFF.USER_NAME.getValue(), null))
                .ifPresent(object -> staffDomainService.setUserName((String) object));
        Optional.ofNullable(map.getOrDefault(Constant.API_SEARCH_PARAM_STAFF.DEPARTMENT_CD.getValue(), null))
                .ifPresent(object -> staffDomainService.setDepartmentCd((String) object));
        Optional.ofNullable(map.getOrDefault(Constant.API_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_START.getValue(), null))
                .ifPresent(object -> {
                    try {
                        staffDomainService.setParamExpirationStart((Date) object);
                    } catch (DomainRuleIllegalException e) {
                        errorMessages.add(e.getMessage());
                    }
                });
        Optional.ofNullable(map.getOrDefault(Constant.API_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_END.getValue(), null))
                .ifPresent(object -> {
                    try {
                        staffDomainService.setParamExpirationEnd((Date) object);
                    } catch (DomainRuleIllegalException e) {
                        errorMessages.add(e.getMessage());
                    }
                });
    }
}
