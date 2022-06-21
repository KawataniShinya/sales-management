package com.sales.application.bean;

import com.sales.domain.genericcode.GenericCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GenericCodeServiceBean {
    private List<GenericCode> genders;
    private List<GenericCode> bloodTypes;
    private List<GenericCode> addressPrefectures;
}
