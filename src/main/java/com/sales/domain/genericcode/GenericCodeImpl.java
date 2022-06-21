package com.sales.domain.genericcode;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Scope("prototype")
public class GenericCodeImpl implements GenericCode{
    @Getter
    @Setter
    private String category;

    @Getter
    @Setter
    private String cd;

    @Getter
    @Setter
    private String displayValue1;

    @Getter
    @Setter
    private String displayValue2;

    @Getter
    @Setter
    private int displayOrder1;

    @Getter
    @Setter
    private int displayOrder2;

    @Override
    public GenericCode createGenericCode() {
        return new GenericCodeImpl();
    }

    @Override
    public void init() {
        this.category = "";
        this.cd = "";
        this.displayValue1 = "";
        this.displayValue2 = "";
        this.displayOrder1 = 0;
        this.displayOrder2 = 0;
    }

    @Override
    public GenericCode setGenericCode(String category, String cd) {
        return null;
    }

    @Override
    public void setFieldsByMapFromDataSource(Map<String, Object> map) {
        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_GENERIC_CD.CATEGORY.getValue(), null))
                .ifPresent(object -> this.setCategory(object.toString()));
        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_GENERIC_CD.CD.getValue(), null))
                .ifPresent(object -> this.setCd(object.toString()));
        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_GENERIC_CD.DISPLAY_VALUE_1.getValue(), null))
                .ifPresent(object -> this.setDisplayValue1(object.toString()));
        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_GENERIC_CD.DISPLAY_VALUE_2.getValue(), null))
                .ifPresent(object -> this.setDisplayValue2(object.toString()));
        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_GENERIC_CD.DISPLAY_ORDER_1.getValue(), null))
                .ifPresent(object -> this.setDisplayOrder1((Integer) object));
        Optional.ofNullable(map.getOrDefault(Constant.DATA_SOURCE_FIELD_NAME_GENERIC_CD.DISPLAY_ORDER_2.getValue(), null))
                .ifPresent(object -> this.setDisplayOrder2((Integer) object));
    }

    @Override
    public void setFieldsByMapFromApi(Map<String, Object> map) {
        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_GENERIC_CD.CATEGORY.getValue(), null))
                .ifPresent(object -> this.setCategory(object.toString()));
        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_GENERIC_CD.CD.getValue(), null))
                .ifPresent(object -> this.setCd(object.toString()));
        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_GENERIC_CD.DISPLAY_VALUE_1.getValue(), null))
                .ifPresent(object -> this.setDisplayValue1(object.toString()));
        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_GENERIC_CD.DISPLAY_VALUE_2.getValue(), null))
                .ifPresent(object -> this.setDisplayValue2(object.toString()));
        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_GENERIC_CD.DISPLAY_ORDER_1.getValue(), null))
                .ifPresent(object -> this.setDisplayOrder1((Integer) object));
        Optional.ofNullable(map.getOrDefault(Constant.API_FIELD_NAME_GENERIC_CD.DISPLAY_ORDER_2.getValue(), null))
                .ifPresent(object -> this.setDisplayOrder2((Integer) object));
    }
}
