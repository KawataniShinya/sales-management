package com.sales.domain.staff;

import com.sales.common.DomainRuleIllegalException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

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

    private final MessageSource messageSource;

    @Autowired
    public StaffDomainServiceImpl(Staff staff, StaffRepository staffRepository, MessageSource messageSource) {
        this.staff = staff;
        this.staffRepository = staffRepository;
        this.messageSource = messageSource;
        this.init();
    }

    @Override
    public StaffDomainService createStaffService() {
        return new StaffDomainServiceImpl(staff, this.staffRepository, this.messageSource);
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
        this.count = this.staffRepository.countUser(this);

        if (this.count < (this.page - 1) * this.limitSize) {
            this.page = this.count / this.limitSize + 1;
        } else if (this.count == (this.page - 1) * this.limitSize) {
            this.page--;
        }
        this.offsetSize = (this.page - 1) * this.limitSize;

        ArrayList<Staff> staffs = new ArrayList<>();
        List<Map<String, Object>> findResult = this.staffRepository.findUser(this);
        findResult.forEach(map -> {
            Staff staff = this.staff.createStaff();
            staff.setFieldsByMapFromDataSource(map);
            staffs.add(staff);
        });
        this.staffs = staffs;
        
        return this;
    }

    public void setParamExpirationStart(Date paramExpirationStart) throws DomainRuleIllegalException {
        this.paramExpirationStart = paramExpirationStart;
        if (this.paramExpirationEnd == null) this.paramExpirationEnd = paramExpirationStart;
        if (this.paramExpirationEnd.before(this.paramExpirationStart)) {
            throw new DomainRuleIllegalException(
                    this.messageSource.getMessage("MSG0001", new String[]{"有効開始日","有効終了日"}, Locale.JAPANESE)
            );
        }
    }

    public void setParamExpirationEnd(Date paramExpirationEnd) throws DomainRuleIllegalException {
        this.paramExpirationEnd = paramExpirationEnd;
        if (this.paramExpirationStart == null) this.paramExpirationStart = paramExpirationEnd;
        if (this.paramExpirationEnd.before(this.paramExpirationStart)) {
            throw new DomainRuleIllegalException(
                    this.messageSource.getMessage("MSG0001", new String[]{"有効開始日","有効終了日"}, Locale.JAPANESE)
            );
        }
    }
}
