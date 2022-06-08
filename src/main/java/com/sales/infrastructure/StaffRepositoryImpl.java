package com.sales.infrastructure;

import com.sales.common.LoggingDelegateRepository;
import com.sales.domain.staff.Constant;
import com.sales.domain.staff.Staff;
import com.sales.domain.staff.StaffRepository;
import com.sales.presentation.DepartmentController;
import com.sales.presentation.dto.DepartmentGetRequest;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Scope("prototype")
@PropertySource(value = "classpath:properties/sql.properties")
public class StaffRepositoryImpl extends AbstractBaseApplicationDbRepository implements StaffRepository {

    private final String APPLSQL002;
    private final DepartmentController departmentController;

    @Autowired
    public StaffRepositoryImpl(
            @Qualifier("appljdbc01") JdbcTemplate jdbcTemplate,
            @Qualifier("applNpjdbc01") NamedParameterJdbcTemplate npJdbcTemplate,
            LoggingDelegateRepository loggingDelegateRepository,
            @Value("${APPLSQL002}") String applsql002,
            DepartmentController departmentController) {
        super(jdbcTemplate, npJdbcTemplate, loggingDelegateRepository);
        this.APPLSQL002 = applsql002;
        this.departmentController = departmentController;
    }

    @Override
    public List<Map<String, Object>> findUserByUserIdInExpiration(Staff staff) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.USER_ID.getValue(), staff.getUserId());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.EXPIRATION_DATE.getValue(), DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_GENERIC_CD.CATEGORY_GENDER.getValue(), Constant.CATEGORY.GENDER.getValue());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_GENERIC_CD.CATEGORY_BLOOD_TYPE.getValue(), Constant.CATEGORY.BLOOD_TYPE.getValue());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_GENERIC_CD.CATEGORY_ADDRESS_PREFECTURE.getValue(), Constant.CATEGORY.ADDRESS_PREFECTURE.getValue());

        super.loggingDelegateRepository.loggingDbDebugPoint(this.getClass(), new Object(){}.getClass().getEnclosingMethod(), "APPLSQL002",this.APPLSQL002 ,paramMap);
        List<Map<String, Object>> resultList = npJdbcTemplate.queryForList(APPLSQL002, paramMap);
        resultList.forEach(map -> {
            DepartmentGetRequest departmentGetRequest = new DepartmentGetRequest();
            departmentGetRequest.setDepartmentCd((String) map.get(Constant.DATA_SOURCE_FIELD_NAME_STAFF.DEPARTMENT_CD.getValue()));
            map.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.DEPARTMENT_NAME.getValue(), this.departmentController.getDepartment(departmentGetRequest).getDepartmentNameJa());
        });
        return resultList;
    }
}
