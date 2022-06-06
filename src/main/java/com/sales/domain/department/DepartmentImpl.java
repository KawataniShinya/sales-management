package com.sales.domain.department;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Scope("prototype")
public class DepartmentImpl implements Department{
    @Getter
    @Setter
    private String departmentCd;

    @Getter
    @Setter
    private String departmentNameEn;

    @Getter
    @Setter
    private String departmentNameJa;

    @Getter
    @Setter
    private Date expirationStart;

    @Getter
    @Setter
    private Date expirationEnd;

    @Getter
    @Setter
    private Date expirationDate;

    @Getter
    @Setter
    private Timestamp insertTimestamp;

    @Getter
    @Setter
    private String insertUser;

    @Getter
    @Setter
    private Timestamp updateTimestamp;

    @Getter
    @Setter
    private String updateUser;

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department createDepartment() {
        return new DepartmentImpl(this.departmentRepository);
    }

    @Override
    public Department setDepartmentByCd(String departmentCd) {
        this.setDepartmentCd(departmentCd);
        List<Map<String, Object>> resultList = this.departmentRepository.findByDepartmentCd(this);
        if (resultList.size() != 1) {
            // exception
        }
        this.setFieldsByMapFromDataSource(resultList.get(0));
        return this;
    }

    @Override
    public void setFieldsByMapFromDataSource(Map<String, Object> map) {
        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_DEPARTMENT.DEPARTMENT_CD.getValue(), null))
                .ifPresent(object -> this.setDepartmentCd(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_DEPARTMENT.DEPARTMENT_NAME_EN.getValue(), null))
                .ifPresent(object -> this.setDepartmentNameEn(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_DEPARTMENT.DEPARTMENT_NAME_JA.getValue(), null))
                .ifPresent(object -> this.setDepartmentNameJa(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_DEPARTMENT.EXPIRATION_START.getValue(), null))
                .ifPresent(object -> this.setExpirationStart((Date) object));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_DEPARTMENT.EXPIRATION_END.getValue(), null))
                .ifPresent(object -> this.setExpirationEnd((Date) object));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_DEPARTMENT.INSERT_TIMESTAMP.getValue(), null))
                .ifPresent(object -> this.setInsertTimestamp((Timestamp) object));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_DEPARTMENT.INSERT_USER.getValue(), null))
                .ifPresent(object -> this.setInsertUser(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_DEPARTMENT.UPDATE_TIMESTAMP.getValue(), null))
                .ifPresent(object -> this.setUpdateTimestamp((Timestamp) object));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_DEPARTMENT.UPDATE_USER.getValue(), null))
                .ifPresent(object -> this.setUpdateUser(object.toString()));
    }

    @Override
    public void setFieldsByMapFromApi(Map<String, Object> map) {
        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_DEPARTMENT.DEPARTMENT_CD.getValue(), null))
                .ifPresent(object -> this.setDepartmentCd(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_DEPARTMENT.DEPARTMENT_NAME_EN.getValue(), null))
                .ifPresent(object -> this.setDepartmentNameEn(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_DEPARTMENT.DEPARTMENT_NAME_JA.getValue(), null))
                .ifPresent(object -> this.setDepartmentNameJa(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_DEPARTMENT.EXPIRATION_START.getValue(), null))
                .ifPresent(object -> this.setExpirationStart((Date) object));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_DEPARTMENT.EXPIRATION_END.getValue(), null))
                .ifPresent(object -> this.setExpirationEnd((Date) object));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_DEPARTMENT.EXPIRATION_DATE.getValue(), null))
                .ifPresent(object -> this.setExpirationDate((Date) object));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_DEPARTMENT.INSERT_TIMESTAMP.getValue(), null))
                .ifPresent(object -> this.setInsertTimestamp((Timestamp) object));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_DEPARTMENT.INSERT_USER.getValue(), null))
                .ifPresent(object -> this.setInsertUser(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_DEPARTMENT.UPDATE_TIMESTAMP.getValue(), null))
                .ifPresent(object -> this.setUpdateTimestamp((Timestamp) object));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_DEPARTMENT.UPDATE_USER.getValue(), null))
                .ifPresent(object -> this.setUpdateUser(object.toString()));
    }
}
