package com.sales.domain.customer;

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
public class CustomerImpl implements Customer{

    @Getter
    @Setter
    private String userId;

    @Getter
    @Setter
    private String familyName;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String personalityCd;

    @Getter
    @Setter
    private String personalityName;

    @Getter
    @Setter
    private String genderCd;

    @Getter
    @Setter
    private String genderName;

    @Getter
    @Setter
    private Date birthdate;

    @Getter
    @Setter
    private String bloodTypeCd;

    @Getter
    @Setter
    private String bloodTypeName;

    @Getter
    @Setter
    private String addressPrefectureCd;

    @Getter
    @Setter
    private String addressPrefectureName;

    @Getter
    @Setter
    private String addressMunicipality;

    @Getter
    @Setter
    private String telNo;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private Date expirationStart;

    @Getter
    @Setter
    private Date expirationEnd;

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

    @Getter
    @Setter
    private Date expirationDate;

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.init();
    }

    @Override
    public Customer createCustomer() {
        return new CustomerImpl(customerRepository);
    }

    @Override
    public void init() {
        this.userId = "";
        this.familyName = "";
        this.firstName = "";
        this.personalityCd = "";
        this.personalityName = "";
        this.genderCd = "";
        this.genderName = "";
        this.birthdate = new Date(0);
        this.bloodTypeCd = "";
        this.bloodTypeName = "";
        this.addressPrefectureCd = "";
        this.addressPrefectureName = "";
        this.addressMunicipality = "";
        this.telNo = "";
        this.email = "";
        this.expirationStart = new Date(0);
        this.expirationEnd = new Date(0);
        this.insertTimestamp = new Timestamp(0);
        this.insertUser = "";
        this.updateTimestamp = new Timestamp(0);
        this.updateUser = "";
        this.expirationDate = new Date(0);
    }

    @Override
    public Customer setCustomerByUserId(String userId) {
        this.setUserId(userId);
        List<Map<String, Object>> resultList = this.customerRepository.findUserByUserIdInExpiration(this);
        if (resultList.size() != 1) {
            // exception
        } else {
            this.setFieldsByMapFromDataSource(resultList.get(0));
        }
        return this;
    }

    @Override
    public void setFieldsByMapFromDataSource(Map<String, Object> map) {
        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.USER_ID.getValue(), null))
                .ifPresent(object -> this.setUserId(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.FAMILY_NAME.getValue(), null))
                .ifPresent(object -> this.setFamilyName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.FIRST_NAME.getValue(), null))
                .ifPresent(object -> this.setFirstName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.PERSONALITY_CD.getValue(), null))
                .ifPresent(object -> this.setPersonalityCd(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.PERSONALITY_NAME.getValue(), null))
                .ifPresent(object -> this.setPersonalityName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.GENDER_CD.getValue(), null))
                .ifPresent(object -> this.setGenderCd(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.GENDER_NAME.getValue(), null))
                .ifPresent(object -> this.setGenderName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.BIRTHDATE.getValue(), null))
                .ifPresent(object -> this.setBirthdate((Date) object));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.BLOOD_TYPE_CD.getValue(), null))
                .ifPresent(object -> this.setBloodTypeCd(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.BLOOD_TYPE_NAME.getValue(), null))
                .ifPresent(object -> this.setBloodTypeName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.ADDRESS_PREFECTURE_CD.getValue(), null))
                .ifPresent(object -> this.setAddressPrefectureCd(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.ADDRESS_PREFECTURE_NAME.getValue(), null))
                .ifPresent(object -> this.setAddressPrefectureName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.ADDRESS_MUNICIPALITY.getValue(), null))
                .ifPresent(object -> this.setAddressMunicipality(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.TEL_NO.getValue(), null))
                .ifPresent(object -> this.setTelNo(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.EMAIL.getValue(), null))
                .ifPresent(object -> this.setEmail(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.EXPIRATION_START.getValue(), null))
                .ifPresent(object -> this.setExpirationStart((Date) object));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.EXPIRATION_END.getValue(), null))
                .ifPresent(object -> this.setExpirationEnd((Date) object));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.INSERT_TIMESTAMP.getValue(), null))
                .ifPresent(object -> this.setInsertTimestamp((Timestamp) object));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.INSERT_USER.getValue(), null))
                .ifPresent(object -> this.setInsertUser(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.UPDATE_TIMESTAMP.getValue(), null))
                .ifPresent(object -> this.setUpdateTimestamp((Timestamp) object));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.UPDATE_USER.getValue(), null))
                .ifPresent(object -> this.setUpdateUser(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_CUSTOMER.EXPIRATION_DATE.getValue(), null))
                .ifPresent(object -> this.setExpirationDate((Date) object));
    }

    @Override
    public void setFieldsByMapFromApi(Map<String, Object> map) {
        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_CUSTOMER.USER_ID.getValue(), null))
                .ifPresent(object -> this.setUserId(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_CUSTOMER.FAMILY_NAME.getValue(), null))
                .ifPresent(object -> this.setFamilyName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_CUSTOMER.FIRST_NAME.getValue(), null))
                .ifPresent(object -> this.setFirstName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_CUSTOMER.PERSONALITY_CD.getValue(), null))
                .ifPresent(object -> this.setPersonalityCd(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_CUSTOMER.PERSONALITY_NAME.getValue(), null))
                .ifPresent(object -> this.setPersonalityName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_CUSTOMER.GENDER_CD.getValue(), null))
                .ifPresent(object -> this.setGenderCd(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_CUSTOMER.GENDER_NAME.getValue(), null))
                .ifPresent(object -> this.setGenderName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_CUSTOMER.BIRTHDATE.getValue(), null))
                .ifPresent(object -> this.setBirthdate((Date) object));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_CUSTOMER.BLOOD_TYPE_CD.getValue(), null))
                .ifPresent(object -> this.setBloodTypeCd(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_CUSTOMER.BLOOD_TYPE_NAME.getValue(), null))
                .ifPresent(object -> this.setBloodTypeName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_CUSTOMER.ADDRESS_PREFECTURE_CD.getValue(), null))
                .ifPresent(object -> this.setAddressPrefectureCd(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_CUSTOMER.ADDRESS_PREFECTURE_NAME.getValue(), null))
                .ifPresent(object -> this.setAddressPrefectureName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_CUSTOMER.ADDRESS_MUNICIPALITY.getValue(), null))
                .ifPresent(object -> this.setAddressMunicipality(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_CUSTOMER.TEL_NO.getValue(), null))
                .ifPresent(object -> this.setTelNo(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_CUSTOMER.EMAIL.getValue(), null))
                .ifPresent(object -> this.setEmail(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_CUSTOMER.EXPIRATION_START.getValue(), null))
                .ifPresent(object -> this.setExpirationStart((Date) object));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_CUSTOMER.EXPIRATION_END.getValue(), null))
                .ifPresent(object -> this.setExpirationEnd((Date) object));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_CUSTOMER.INSERT_TIMESTAMP.getValue(), null))
                .ifPresent(object -> this.setInsertTimestamp((Timestamp) object));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_CUSTOMER.INSERT_USER.getValue(), null))
                .ifPresent(object -> this.setInsertUser(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_CUSTOMER.UPDATE_TIMESTAMP.getValue(), null))
                .ifPresent(object -> this.setUpdateTimestamp((Timestamp) object));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_CUSTOMER.UPDATE_USER.getValue(), null))
                .ifPresent(object -> this.setUpdateUser(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_CUSTOMER.EXPIRATION_DATE.getValue(), null))
                .ifPresent(object -> this.setExpirationDate((Date) object));
    }
}
