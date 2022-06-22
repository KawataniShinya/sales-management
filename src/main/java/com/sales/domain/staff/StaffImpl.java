package com.sales.domain.staff;

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
public class StaffImpl implements Staff{

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
    private String departmentCd;

    @Getter
    @Setter
    private String departmentName;

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
    private String privateTelNo;

    @Getter
    @Setter
    private String privateEmail;

    @Getter
    @Setter
    private String workplaceTelNo;

    @Getter
    @Setter
    private String workplaceEmail;

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

    private final StaffRepository staffRepository;

    @Autowired
    public StaffImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
        this.init();
    }

    @Override
    public Staff createStaff() {
        return new StaffImpl(this.staffRepository);
    }

    @Override
    public void init() {
        this.userId = "";
        this.familyName = "";
        this.firstName = "";
        this.departmentCd = "";
        this.departmentName = "";
        this.genderCd = "";
        this.genderName = "";
        this.birthdate = new Date(0);
        this.bloodTypeCd = "";
        this.bloodTypeName = "";
        this.addressPrefectureCd = "";
        this.addressPrefectureName = "";
        this.addressMunicipality = "";
        this.privateTelNo = "";
        this.privateEmail = "";
        this.workplaceTelNo = "";
        this.workplaceEmail = "";
        this.expirationStart = new Date(0);
        this.expirationEnd = new Date(0);
        this.insertTimestamp = new Timestamp(0);
        this.insertUser = "";
        this.updateTimestamp = new Timestamp(0);
        this.updateUser = "";
        this.expirationDate = new Date(0);
    }

    @Override
    public Staff setStaffByUserId(String userId) {
        this.setUserId(userId);
        List<Map<String, Object>> resultList = this.staffRepository.findUserByUserIdInExpiration(this);
        if (resultList.size() != 1) {
            // exception
        } else {
            this.setFieldsByMapFromDataSource(resultList.get(0));
        }
        return this;
    }

    @Override
    public void setFieldsByMapFromDataSource(Map<String, Object> map) {
        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.USER_ID.getValue(), null))
                .ifPresent(object -> this.setUserId(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.FAMILY_NAME.getValue(), null))
                .ifPresent(object -> this.setFamilyName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.FIRST_NAME.getValue(), null))
                .ifPresent(object -> this.setFirstName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.DEPARTMENT_CD.getValue(), null))
                .ifPresent(object -> this.setDepartmentCd(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.DEPARTMENT_NAME.getValue(), null))
                .ifPresent(object -> this.setDepartmentName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.GENDER_CD.getValue(), null))
                .ifPresent(object -> this.setGenderCd(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.GENDER_NAME.getValue(), null))
                .ifPresent(object -> this.setGenderName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.BIRTHDATE.getValue(), null))
                .ifPresent(object -> this.setBirthdate((Date) object));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.BLOOD_TYPE_CD.getValue(), null))
                .ifPresent(object -> this.setBloodTypeCd(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.BLOOD_TYPE_NAME.getValue(), null))
                .ifPresent(object -> this.setBloodTypeName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.ADDRESS_PREFECTURE_CD.getValue(), null))
                .ifPresent(object -> this.setAddressPrefectureCd(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.ADDRESS_PREFECTURE_NAME.getValue(), null))
                .ifPresent(object -> this.setAddressPrefectureName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.ADDRESS_MUNICIPALITY.getValue(), null))
                .ifPresent(object -> this.setAddressMunicipality(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.PRIVATE_TEL_NO.getValue(), null))
                .ifPresent(object -> this.setPrivateTelNo(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.PRIVATE_EMAIL.getValue(), null))
                .ifPresent(object -> this.setPrivateEmail(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.WORKPLACE_TEL_NO.getValue(), null))
                .ifPresent(object -> this.setWorkplaceTelNo(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.WORKPLACE_EMAIL.getValue(), null))
                .ifPresent(object -> this.setWorkplaceEmail(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.EXPIRATION_START.getValue(), null))
                .ifPresent(object -> this.setExpirationStart((Date) object));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.EXPIRATION_END.getValue(), null))
                .ifPresent(object -> this.setExpirationEnd((Date) object));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.INSERT_TIMESTAMP.getValue(), null))
                .ifPresent(object -> this.setInsertTimestamp((Timestamp) object));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.INSERT_USER.getValue(), null))
                .ifPresent(object -> this.setInsertUser(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.UPDATE_TIMESTAMP.getValue(), null))
                .ifPresent(object -> this.setUpdateTimestamp((Timestamp) object));

        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_STAFF.UPDATE_USER.getValue(), null))
                .ifPresent(object -> this.setUpdateUser(object.toString()));
    }

    @Override
    public void setFieldsByMapFromApi(Map<String, Object> map) {
        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.USER_ID.getValue(), null))
                .ifPresent(object -> this.setUserId(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.FAMILY_NAME.getValue(), null))
                .ifPresent(object -> this.setFamilyName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.FIRST_NAME.getValue(), null))
                .ifPresent(object -> this.setFirstName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.DEPARTMENT_CD.getValue(), null))
                .ifPresent(object -> this.setDepartmentCd(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.DEPARTMENT_NAME.getValue(), null))
                .ifPresent(object -> this.setDepartmentName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.GENDER_CD.getValue(), null))
                .ifPresent(object -> this.setGenderCd(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.GENDER_NAME.getValue(), null))
                .ifPresent(object -> this.setGenderName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.BIRTHDATE.getValue(), null))
                .ifPresent(object -> this.setBirthdate((Date) object));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.BLOOD_TYPE_CD.getValue(), null))
                .ifPresent(object -> this.setBloodTypeCd(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.BLOOD_TYPE_NAME.getValue(), null))
                .ifPresent(object -> this.setBloodTypeName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.ADDRESS_PREFECTURE_CD.getValue(), null))
                .ifPresent(object -> this.setAddressPrefectureCd(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.ADDRESS_PREFECTURE_NAME.getValue(), null))
                .ifPresent(object -> this.setAddressPrefectureName(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.ADDRESS_MUNICIPALITY.getValue(), null))
                .ifPresent(object -> this.setAddressMunicipality(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.PRIVATE_TEL_NO.getValue(), null))
                .ifPresent(object -> this.setPrivateTelNo(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.PRIVATE_EMAIL.getValue(), null))
                .ifPresent(object -> this.setPrivateEmail(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.WORKPLACE_TEL_NO.getValue(), null))
                .ifPresent(object -> this.setWorkplaceTelNo(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.WORKPLACE_EMAIL.getValue(), null))
                .ifPresent(object -> this.setWorkplaceEmail(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.EXPIRATION_START.getValue(), null))
                .ifPresent(object -> this.setExpirationStart((Date) object));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.EXPIRATION_END.getValue(), null))
                .ifPresent(object -> this.setExpirationEnd((Date) object));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.INSERT_TIMESTAMP.getValue(), null))
                .ifPresent(object -> this.setInsertTimestamp((Timestamp) object));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.INSERT_USER.getValue(), null))
                .ifPresent(object -> this.setInsertUser(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.UPDATE_TIMESTAMP.getValue(), null))
                .ifPresent(object -> this.setUpdateTimestamp((Timestamp) object));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.UPDATE_USER.getValue(), null))
                .ifPresent(object -> this.setUpdateUser(object.toString()));

        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_STAFF.EXPIRATION_DATE.getValue(), null))
                .ifPresent(object -> this.setExpirationDate((Date) object));
    }
}
