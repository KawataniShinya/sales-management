package com.sales.application;

import com.sales.application.bean.StaffServiceBean;
import com.sales.domain.staff.Constant;
import com.sales.domain.staff.StaffDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Scope("prototype")
public class StaffServiceImpl implements StaffService{
    private final StaffDomainService staffDomainService;

    @Autowired
    public StaffServiceImpl(StaffDomainService staffDomainService) {
        this.staffDomainService = staffDomainService;
    }

    @Override
    public StaffServiceBean findStaffs(Map<String, Object> map) {
        StaffServiceBean staffServiceBean = new StaffServiceBean();
        StaffDomainService staffDomainService = this.staffDomainService.createStaffService();
        staffDomainService.setLimitSize((Integer) map.get(Constant.API_FIELD_NAME_STAFF.LIMIT_SIZE.getValue()));
        staffDomainService.setOffsetSize((Integer) map.get(Constant.API_FIELD_NAME_STAFF.OFFSET_SIZE.getValue()));
        staffServiceBean.setStaffs(staffDomainService.findAllUser().getStaffs());
        return staffServiceBean;
    }
}
