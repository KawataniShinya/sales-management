package com.sales.domain.department;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

public interface Department {
    void setDepartmentCd(String departmentCd);
    String getDepartmentCd();
    void setDepartmentNameEn(String departmentNameEn);
    String getDepartmentNameEn();
    void setDepartmentNameJa(String departmentNameJa);
    String getDepartmentNameJa();
    void setExpirationStart(Date expirationStart);
    Date getExpirationStart();
    void setExpirationEnd(Date expirationEnd);
    Date getExpirationEnd();
    void setExpirationDate(Date expirationDate);
    Date getExpirationDate();
    void setInsertTimestamp(Timestamp insertTimestamp);
    Timestamp getInsertTimestamp();
    void setInsertUser(String insertUser);
    String getInsertUser();
    void setUpdateTimestamp(Timestamp updateTimestamp);
    Timestamp getUpdateTimestamp();
    void setUpdateUser(String updateUser);
    String getUpdateUser();

    Department createDepartment();

    Department setDepartmentByCd(String departmentCd);

    void setFieldsByMapFromDataSource(Map<String, Object> map);
    void setFieldsByMapFromApi(Map<String, Object> map);
}
