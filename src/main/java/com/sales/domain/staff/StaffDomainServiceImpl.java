package com.sales.domain.staff;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Scope("prototype")
public class StaffDomainServiceImpl implements StaffDomainService {
    @Getter
    @Setter
    private long count;

    @Getter
    @Setter
    private long limitSize;

    @Getter
    @Setter
    private long offsetSize;

    @Getter
    @Setter
    private long page;

    @Getter
    @Setter
    private String userId;

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private String departmentCd;

    @Getter
    private Date paramExpirationStart;

    @Getter
    private Date paramExpirationEnd;

    @Getter
    @Setter
    private List<Staff> staffs;

    private final Staff staff;

    private final StaffRepository staffRepository;

    @Autowired
    public StaffDomainServiceImpl(Staff staff, StaffRepository staffRepository) {
        this.staff = staff;
        this.staffRepository = staffRepository;
        this.init();
    }

    @Override
    public StaffDomainService createStaffService() {
        return new StaffDomainServiceImpl(staff, this.staffRepository);
    }

    @Override
    public void init() {
        this.count = 0;
        this.limitSize = 0;
        this.offsetSize = 0;
        this.staffs = new ArrayList<>();
        this.staffs.add(this.staff.createStaff());
        this.userId = "";
        this.userName = "";
        this.departmentCd = "";
        this.paramExpirationStart = null;
        this.paramExpirationEnd = null;
    }

    @Override
    public StaffDomainService findAllUser() {
        this.count = this.staffRepository.countAllUser(this);
        this.staffRepository.countUser(this);

        if (this.count < (this.page - 1) * this.limitSize) {
            this.page = this.count / this.limitSize + 1;
        } else if (this.count == (this.page - 1) * this.limitSize) {
            this.page--;
        }
        this.offsetSize = (this.page - 1) * this.limitSize;

        ArrayList<Staff> staffs = new ArrayList<>();
        List<Map<String, Object>> findResult = this.staffRepository.findAllUser(this);
        findResult.forEach(map -> {
            Staff staff = this.staff.createStaff();
            staff.setFieldsByMapFromDataSource(map);
            staffs.add(staff);
        });
        this.staffs = staffs;
        
        return this;
    }

    public void setParamExpirationStart(Date paramExpirationStart) {
        this.paramExpirationStart = paramExpirationStart;
        if (this.paramExpirationEnd == null) this.paramExpirationEnd = paramExpirationStart;
    }

    public void setParamExpirationEnd(Date paramExpirationEnd) {
        this.paramExpirationEnd = paramExpirationEnd;
        if (this.paramExpirationStart == null) this.paramExpirationStart = paramExpirationEnd;
    }
}
