package com.sales.domain.customer;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

public interface Customer {
    void setUserId(String userId);
    String getUserId();
    void setFamilyName(String familyName);
    String getFamilyName();
    void setFirstName(String firstName);
    String getFirstName();
    void setPersonalityCd(String personalityCd);
    String getPersonalityCd();
    void setPersonalityName(String personalityName);  // Expansion
    String getPersonalityName();  // Expansion
    void setGenderCd(String genderCd);
    String getGenderCd();
    void setGenderName(String genderName);  // Expansion
    String getGenderName();  // Expansion
    void setBirthdate(Date birthdate);
    Date getBirthdate();
    void setBloodTypeCd(String bloodTypeCd);
    String getBloodTypeCd();
    void setBloodTypeName(String bloodTypeName);  // Expansion
    String getBloodTypeName();  // Expansion
    void setAddressPrefectureCd(String addressPrefectureCd);
    String getAddressPrefectureCd();
    void setAddressPrefectureName(String addressPrefectureName);  // Expansion
    String getAddressPrefectureName();  // Expansion
    void setAddressMunicipality(String addressMunicipality);
    String getAddressMunicipality();
    void setTelNo(String telNo);
    String getTelNo();
    void setEmail(String email);
    String getEmail();
    void setExpirationStart(Date expirationStart);
    Date getExpirationStart();
    void setExpirationEnd(Date expirationEnd);
    Date getExpirationEnd();
    void setInsertTimestamp(Timestamp insertTimestamp);
    Timestamp getInsertTimestamp();
    void setInsertUser(String insertUser);
    String getInsertUser();
    void setUpdateTimestamp(Timestamp updateTimestamp);
    Timestamp getUpdateTimestamp();
    void setUpdateUser(String updateUser);
    String getUpdateUser();
    void setExpirationDate(Date expirationDate);  // Expansion
    Date getExpirationDate();  // Expansion

    Customer createCustomer();

    void init();

    Customer setCustomerByUserId(String userId);

    void setFieldsByMapFromDataSource(Map<String, Object> map);
    void setFieldsByMapFromApi(Map<String, Object> map);
}
