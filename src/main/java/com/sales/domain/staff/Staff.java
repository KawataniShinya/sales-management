package com.sales.domain.staff;

import com.sales.common.DomainRuleIllegalException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

public interface Staff {
    void setUserId(String userId);
    String getUserId();
    void setFamilyName(String familyName);
    String getFamilyName();
    void setFirstName(String firstName);
    String getFirstName();
    void setDepartmentCd(String departmentCd);
    String getDepartmentCd();
    void setDepartmentName(String departmentName);  // Expansion
    String getDepartmentName(); // Expansion
    void setGenderCd(String genderCd);
    String getGenderCd();
    void setGenderName(String genderName);   // Expansion
    String getGenderName();  // Expansion
    void setBirthdate(Date birthdate);
    Date getBirthdate();
    void setBloodTypeCd(String bloodTypeCd);
    String getBloodTypeCd();
    void setBloodTypeName(String bloodTypeName);    // Expansion
    String getBloodTypeName();  // Expansion
    void setAddressPrefectureCd(String addressPrefectureCd);
    String getAddressPrefectureCd();
    void setAddressPrefectureName(String addressPrefectureName);    // Expansion
    String getAddressPrefectureName();  // Expansion
    void setAddressMunicipality(String addressMunicipality);
    String getAddressMunicipality();
    void setPrivateTelNo(String privateTelNo);
    String getPrivateTelNo();
    void setPrivateEmail(String privateEmail);
    String getPrivateEmail();
    void setWorkplaceTelNo(String workplaceTelNo);
    String getWorkplaceTelNo();
    void setWorkplaceEmail(String workplaceEmail);
    String getWorkplaceEmail();
    void setExpirationStart(Date expirationStart) throws DomainRuleIllegalException;
    Date getExpirationStart();
    void setExpirationEnd(Date expirationEnd) throws DomainRuleIllegalException;
    Date getExpirationEnd();
    void setInsertTimestamp(Timestamp insertTimestamp);
    Timestamp getInsertTimestamp();
    void setInsertUser(String insertUser);
    String getInsertUser();
    void setUpdateTimestamp(Timestamp updateTimestamp);
    Timestamp getUpdateTimestamp();
    void setUpdateUser(String updateUser);
    String getUpdateUser();
    void setExpirationDate(Date expirationDate);
    Date getExpirationDate();

    Staff createStaff();

    void init();

    Staff setStaffByUserId(String userId);
    void addStaff();
    void deleteStaff();

    void setFieldsByMapFromDataSource(Map<String, Object> map);
    void setFieldsByMapFromApi(Map<String, Object> map) throws DomainRuleIllegalException;
}
