package com.sales.domain.genericcode;

import java.util.ArrayList;
import java.util.List;

public interface GenericCodeDomainService {
    static final List<GenericCode> genders = new ArrayList<>();
    static final List<GenericCode> bloodType = new ArrayList<>();
    static final List<GenericCode> addressPrefecture = new ArrayList<>();

    static void setGenders(List<GenericCode> list) {
        genders.addAll(list);
    }

    static List<GenericCode> getGenders() {
        return genders;
    }

    static void setBloodType(List<GenericCode> list) {
        bloodType.addAll(list);
    }

    static List<GenericCode> getBloodType() {
        return bloodType;
    }

    static void setAddressPrefecture(List<GenericCode> list) {
        addressPrefecture.addAll(list);
    }

    static List<GenericCode> getAddressPrefecture() {
        return addressPrefecture;
    }

    void setCategory(String category);
    String getCategory();

    List<GenericCode> getGenderList();
    List<GenericCode> getBloodTypeList();
    List<GenericCode> getAddressPrefectureList();
}
