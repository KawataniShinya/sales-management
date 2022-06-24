package com.sales.infrastructure;

import com.sales.common.LoggingDelegateRepository;
import com.sales.domain.staff.Constant;
import com.sales.domain.staff.Staff;
import com.sales.domain.staff.StaffRepository;
import com.sales.domain.staff.StaffDomainService;
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
    private final String APPLSQL005;
    private final String APPLSQL006;
    private final String APPLSQL009;
    private final String APPLSQL010;
    private final String APPLSQL011;
    private final DepartmentController departmentController;

    @Autowired
    public StaffRepositoryImpl(
            @Qualifier("appljdbc01") JdbcTemplate jdbcTemplate,
            @Qualifier("applNpjdbc01") NamedParameterJdbcTemplate npJdbcTemplate,
            LoggingDelegateRepository loggingDelegateRepository,
            @Value("${APPLSQL002}") String applsql002,
            @Value("${APPLSQL005}") String applsql005,
            @Value("${APPLSQL006}") String applsql006,
            @Value("${APPLSQL009}") String applsql009,
            @Value("${APPLSQL010}") String applsql010,
            @Value("${APPLSQL011}") String applsql011,
            DepartmentController departmentController) {
        super(jdbcTemplate, npJdbcTemplate, loggingDelegateRepository);
        this.APPLSQL002 = applsql002;
        this.APPLSQL005 = applsql005;
        this.APPLSQL006 = applsql006;
        this.APPLSQL009 = applsql009;
        this.APPLSQL010 = applsql010;
        this.APPLSQL011 = applsql011;
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

//        super.loggingDelegateRepository.loggingDbDebugPoint(this.getClass(), new Object(){}.getClass().getEnclosingMethod(), "APPLSQL002",this.APPLSQL002 ,paramMap);
        List<Map<String, Object>> resultList = npJdbcTemplate.queryForList(APPLSQL002, paramMap);
        resultList.forEach(map -> {
            DepartmentGetRequest departmentGetRequest = new DepartmentGetRequest();
            departmentGetRequest.setDepartmentCd((String) map.get(Constant.DATA_SOURCE_FIELD_NAME_STAFF.DEPARTMENT_CD.getValue()));
            map.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.DEPARTMENT_NAME.getValue(), this.departmentController.getDepartment(departmentGetRequest).getDepartmentNameJa());
        });
        return resultList;
    }

    @Override
    public long countUser(StaffDomainService staffDomainService) {
        Map<String, Object> paramMap = new HashMap<>();
        String editSql = this.APPLSQL005;
        editSql = removeWhereClausesOrSetParams(staffDomainService, paramMap, editSql);

        super.loggingDelegateRepository.loggingDbDebugPoint(this.getClass(), new Object(){}.getClass().getEnclosingMethod(), "APPLSQL005", editSql, paramMap);
        List<Map<String, Object>> resultList = npJdbcTemplate.queryForList(editSql, paramMap);

        return (long) resultList.get(0).get(Constant.DATA_SOURCE_SEARCH_PARAM_STAFF.COUNT.getValue());
    }

    @Override
    public List<Map<String, Object>> findUser(StaffDomainService staffDomainService) {
        Map<String, Object> paramMap = new HashMap<>();
        String editSql = this.APPLSQL006;
        editSql = removeWhereClausesOrSetParams(staffDomainService, paramMap, editSql);
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_GENERIC_CD.CATEGORY_GENDER.getValue(), Constant.CATEGORY.GENDER.getValue());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_GENERIC_CD.CATEGORY_BLOOD_TYPE.getValue(), Constant.CATEGORY.BLOOD_TYPE.getValue());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_GENERIC_CD.CATEGORY_ADDRESS_PREFECTURE.getValue(), Constant.CATEGORY.ADDRESS_PREFECTURE.getValue());
        paramMap.put(Constant.DATA_SOURCE_SEARCH_PARAM_STAFF.LIMIT_SIZE.getValue(), staffDomainService.getLimitSize());
        paramMap.put(Constant.DATA_SOURCE_SEARCH_PARAM_STAFF.OFFSET_SIZE.getValue(), staffDomainService.getOffsetSize());

        super.loggingDelegateRepository.loggingDbDebugPoint(this.getClass(), new Object(){}.getClass().getEnclosingMethod(), "APPLSQL006", editSql, paramMap);
        List<Map<String, Object>> resultList = npJdbcTemplate.queryForList(editSql, paramMap);
        resultList.forEach(map -> {
            DepartmentGetRequest departmentGetRequest = new DepartmentGetRequest();
            departmentGetRequest.setDepartmentCd((String) map.get(Constant.DATA_SOURCE_FIELD_NAME_STAFF.DEPARTMENT_CD.getValue()));
            map.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.DEPARTMENT_NAME.getValue(), this.departmentController.getDepartment(departmentGetRequest).getDepartmentNameJa());
        });

        return resultList;
    }

    @Override
    public long countUserNewer(StaffDomainService staffDomainService) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(Constant.DATA_SOURCE_SEARCH_PARAM_STAFF.USER_ID.getValue(), staffDomainService.getUserId());
        paramMap.put(Constant.DATA_SOURCE_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_START.getValue(), staffDomainService.getParamExpirationStart());

        super.loggingDelegateRepository.loggingDbDebugPoint(this.getClass(), new Object(){}.getClass().getEnclosingMethod(), "APPLSQL005", APPLSQL009, paramMap);
        List<Map<String, Object>> resultList = npJdbcTemplate.queryForList(APPLSQL009, paramMap);

        return (long) resultList.get(0).get(Constant.DATA_SOURCE_SEARCH_PARAM_STAFF.COUNT.getValue());
    }

    @Override
    public void insertStaff(Staff staff) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.USER_ID.getValue(), staff.getUserId());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.FAMILY_NAME.getValue(), staff.getFamilyName());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.FIRST_NAME.getValue(), staff.getFirstName());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.DEPARTMENT_CD.getValue(), staff.getDepartmentCd());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.GENDER_CD.getValue(), staff.getGenderCd());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.BIRTHDATE.getValue(), staff.getBirthdate());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.BLOOD_TYPE_CD.getValue(), staff.getBloodTypeCd());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.ADDRESS_PREFECTURE_CD.getValue(), staff.getAddressPrefectureCd());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.ADDRESS_MUNICIPALITY.getValue(), staff.getAddressMunicipality());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.PRIVATE_TEL_NO.getValue(), staff.getPrivateTelNo());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.PRIVATE_EMAIL.getValue(), staff.getPrivateEmail());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.WORKPLACE_TEL_NO.getValue(), staff.getWorkplaceTelNo());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.WORKPLACE_EMAIL.getValue(), staff.getWorkplaceEmail());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.EXPIRATION_START.getValue(), staff.getExpirationStart());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.EXPIRATION_END.getValue(), staff.getExpirationEnd());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.INSERT_USER.getValue(), staff.getInsertUser());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.UPDATE_USER.getValue(), staff.getUpdateUser());

        super.loggingDelegateRepository.loggingDbDebugPoint(this.getClass(), new Object(){}.getClass().getEnclosingMethod(), "APPLSQL010", APPLSQL010, paramMap);
        npJdbcTemplate.update(APPLSQL010, paramMap);
    }

    @Override
    public void updateExpirationEndLastBefore(StaffDomainService staffDomainService) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.USER_ID.getValue(), staffDomainService.getUserId());
        paramMap.put(Constant.DATA_SOURCE_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_START.getValue(), staffDomainService.getParamExpirationStart());
        paramMap.put(Constant.DATA_SOURCE_FIELD_NAME_STAFF.EXPIRATION_END.getValue(), staffDomainService.getExpirationEnd());

        super.loggingDelegateRepository.loggingDbDebugPoint(this.getClass(), new Object(){}.getClass().getEnclosingMethod(), "APPLSQL011", APPLSQL011, paramMap);
        npJdbcTemplate.update(APPLSQL011, paramMap);
    }

    private String removeWhereClausesOrSetParams(StaffDomainService staffDomainService, Map<String, Object> paramMap, String editSql) {
        if (staffDomainService.getUserId().equals("")) {
            editSql = editSql.replace("STAFF.USER_ID = :USER_ID and", "");
        } else {
            paramMap.put(Constant.DATA_SOURCE_SEARCH_PARAM_STAFF.USER_ID.getValue(), staffDomainService.getUserId());
        }
        if (staffDomainService.getUserName().equals("")) {
            editSql = editSql.replace("concat(STAFF.FAMILY_NAME, STAFF.FIRST_NAME) like :USER_NAME and", "");
        } else {
            paramMap.put(Constant.DATA_SOURCE_SEARCH_PARAM_STAFF.USER_NAME.getValue(), "%" + staffDomainService.getUserName() + "%");
        }
        if (staffDomainService.getDepartmentCd().equals("")) {
            editSql = editSql.replace("STAFF.DEPARTMENT_CD = :DEPARTMENT_CD and", "");
        } else {
            paramMap.put(Constant.DATA_SOURCE_SEARCH_PARAM_STAFF.DEPARTMENT_CD.getValue(), staffDomainService.getDepartmentCd());
        }
        if (staffDomainService.getParamExpirationStart() == null || staffDomainService.getParamExpirationEnd() == null) {
            editSql = editSql.replace(
                    "(:PARAM_EXPIRATION_START between STAFF.EXPIRATION_START and STAFF.EXPIRATION_END or :PARAM_EXPIRATION_END between STAFF.EXPIRATION_START and STAFF.EXPIRATION_END) and",
                    "");
        } else {
            paramMap.put(Constant.DATA_SOURCE_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_START.getValue(), staffDomainService.getParamExpirationStart());
            paramMap.put(Constant.DATA_SOURCE_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_END.getValue(), staffDomainService.getParamExpirationEnd());
        }
        return editSql;
    }

}
