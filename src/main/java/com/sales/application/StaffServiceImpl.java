package com.sales.application;

import com.sales.application.bean.StaffServiceBean;
import com.sales.common.DomainRuleIllegalException;
import com.sales.domain.staff.Constant;
import com.sales.domain.staff.Staff;
import com.sales.domain.staff.StaffDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Scope("prototype")
@Transactional(transactionManager = "applTransactionManager")
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

        try {
            getStaffByParam(map);
        } catch (DomainRuleIllegalException e) {
            errorMessages.addAll(e.getMessages());
        }

        if (!errorMessages.isEmpty()) {
            throw new DomainRuleIllegalException(errorMessages);
        }
    }

    @Override
    public Staff getStaffByParam(Map<String, Object> map) throws DomainRuleIllegalException {
        Staff staff = this.staff.createStaff();
        staff.setFieldsByMapFromApi(map);
        return staff;
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

    @Override
    public void addStaff(Map<String, Object> map) throws DomainRuleIllegalException {
        this.checkAddStaff(map);
        Staff staff = this.getStaffByParam(map);
        staff.addStaff();

        StaffDomainService staffDomainService = this.staffDomainService.createStaffService();
        staffDomainService.setUserId(staff.getUserId());
        staffDomainService.setParamExpirationStart(staff.getExpirationStart());
        staffDomainService.updateExpirationEndLastBeforeAdded();
    }

    @Override
    public void checkDeleteStaff(Map<String, Object> map) throws DomainRuleIllegalException {
        List<String> errorMessages = new ArrayList<>();

        StaffDomainService staffDomainService = this.staffDomainService.createStaffService();
        staffDomainService.setUserId((String) map.get(Constant.API_FIELD_NAME_STAFF.USER_ID.getValue()));
        staffDomainService.setParamExpirationStart((Date) map.get(Constant.API_FIELD_NAME_STAFF.EXPIRATION_START.getValue()));
        try {
            staffDomainService.checkDeleteStaff();
        } catch (DomainRuleIllegalException e) {
            errorMessages.addAll(e.getMessages());
        }

        if (!errorMessages.isEmpty()) {
            throw new DomainRuleIllegalException(errorMessages);
        }
    }

    @Override
    public void deleteStaff(Map<String, Object> map) throws DomainRuleIllegalException {
        this.checkDeleteStaff(map);
        Staff staff = this.getStaffByParam(map);
        staff.deleteStaff();

        StaffDomainService staffDomainService = this.staffDomainService.createStaffService();
        staffDomainService.setUserId(staff.getUserId());
        staffDomainService.setParamExpirationStart(staff.getExpirationStart());
        staffDomainService.updateExpirationEndLastBeforeDeleted();
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
