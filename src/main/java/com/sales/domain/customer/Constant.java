package com.sales.domain.customer;

public class Constant {
    public enum DATA_SOURCE_FIELD_NAME_CUSTOMER {
        USER_ID("USER_ID"),
        FAMILY_NAME("FAMILY_NAME"),
        FIRST_NAME("FIRST_NAME"),
        PERSONALITY_CD("PERSONALITY_CD"),
        PERSONALITY_NAME("PERSONALITY_NAME"),  // Expansion
        GENDER_CD("GENDER_CD"),
        GENDER_NAME("GENDER_NAME"),   // Expansion
        BIRTHDATE("BIRTHDATE"),
        BLOOD_TYPE_CD("BLOOD_TYPE_CD"),
        BLOOD_TYPE_NAME("BLOOD_TYPE_NAME"),   // Expansion
        ADDRESS_PREFECTURE_CD("ADDRESS_PREFECTURE_CD"),
        ADDRESS_PREFECTURE_NAME("ADDRESS_PREFECTURE_NAME"),   // Expansion
        ADDRESS_MUNICIPALITY("ADDRESS_MUNICIPALITY"),
        TEL_NO("TEL_NO"),
        EMAIL("EMAIL"),
        EXPIRATION_START("EXPIRATION_START"),
        EXPIRATION_END("EXPIRATION_END"),
        INSERT_TIMESTAMP("INSERT_TIMESTAMP"),
        INSERT_USER("INSERT_USER"),
        UPDATE_TIMESTAMP("UPDATE_TIMESTAMP"),
        UPDATE_USER("UPDATE_USER"),
        EXPIRATION_DATE("EXPIRATION_DATE"); // Expansion

        private final String value;
        DATA_SOURCE_FIELD_NAME_CUSTOMER(String value) {this.value = value;}
        public String getValue() {
            return this.value;
        }
    }

    public enum API_FIELD_NAME_CUSTOMER {
        USER_ID("userId"),
        FAMILY_NAME("familyName"),
        FIRST_NAME("firstName"),
        PERSONALITY_CD("personalityCd"),
        PERSONALITY_NAME("personalityName"),  // Expansion
        GENDER_CD("genderCd"),
        GENDER_NAME("genderName"),  // Expansion
        BIRTHDATE("birthdate"),
        BLOOD_TYPE_CD("bloodTypeCd"),
        BLOOD_TYPE_NAME("bloodTypeName"),  // Expansion
        ADDRESS_PREFECTURE_CD("addressPrefectureCd"),
        ADDRESS_PREFECTURE_NAME("addressPrefectureName"),  // Expansion
        ADDRESS_MUNICIPALITY("addressMunicipality"),
        TEL_NO("TelNo"),
        EMAIL("Email"),
        EXPIRATION_START("expirationStart"),
        EXPIRATION_END("expirationEnd"),
        INSERT_TIMESTAMP("insertTimestamp"),
        INSERT_USER("insertUser"),
        UPDATE_TIMESTAMP("updateTimestamp"),
        UPDATE_USER("updateUser"),
        EXPIRATION_DATE("expirationDate"); // Expansion

        private final String value;
        API_FIELD_NAME_CUSTOMER(String value) {this.value = value;}
        public String getValue() {
            return this.value;
        }
    }

    public enum CATEGORY {
        PERSONALITY("0001"),
        GENDER("0002"),
        BLOOD_TYPE("0004"),
        ADDRESS_PREFECTURE("0005");

        private final String value;
        CATEGORY(String value) {
            this.value = value;
        }
        public String getValue() {
            return this.value;
        }
    }

    public enum DATA_SOURCE_FIELD_NAME_GENERIC_CD {
        CATEGORY_PERSONALITY("CATEGORY_PERSONALITY"),
        CATEGORY_GENDER("CATEGORY_GENDER"),
        CATEGORY_BLOOD_TYPE("CATEGORY_BLOOD_TYPE"),
        CATEGORY_ADDRESS_PREFECTURE("CATEGORY_ADDRESS_PREFECTURE");

        private final String value;
        DATA_SOURCE_FIELD_NAME_GENERIC_CD(String value) {this.value = value;}
        public String getValue() {
            return this.value;
        }
    }
}
