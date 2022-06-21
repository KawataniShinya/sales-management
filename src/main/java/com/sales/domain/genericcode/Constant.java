package com.sales.domain.genericcode;

public class Constant {
    public enum DATA_SOURCE_FIELD_NAME_GENERIC_CD {
        CATEGORY("CATEGORY"),
        CD("CD"),
        DISPLAY_VALUE_1("DISPLAY_VALUE_1"),
        DISPLAY_VALUE_2("DISPLAY_VALUE_2"),
        DISPLAY_ORDER_1("DISPLAY_ORDER_1"),
        DISPLAY_ORDER_2("DISPLAY_ORDER_2");

        private final String value;
        DATA_SOURCE_FIELD_NAME_GENERIC_CD(String value) {this.value = value;}
        public String getValue() {
            return this.value;
        }
    }

    public enum API_FIELD_NAME_GENERIC_CD {
        CATEGORY("category"),
        CD("cd"),
        DISPLAY_VALUE_1("displayValue1"),
        DISPLAY_VALUE_2("displayValue2"),
        DISPLAY_ORDER_1("displayOrder1"),
        DISPLAY_ORDER_2("displayOrder2"),
        GENDERS("genders"),
        BLOOD_TYPES("bloodTypes"),
        ADDRESS_PREFECTURES("addressPrefectures");

        private final String value;
        API_FIELD_NAME_GENERIC_CD(String value) {this.value = value;}
        public String getValue() {
            return this.value;
        }
    }

    public enum CATEGORY {
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
}
