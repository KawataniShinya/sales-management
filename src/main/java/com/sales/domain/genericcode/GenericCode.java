package com.sales.domain.genericcode;

import java.util.Map;

public interface GenericCode {
    void setCategory(String category);
    String getCategory();
    void setCd(String cd);
    String getCd();
    void setDisplayValue1(String displayValue1);
    String getDisplayValue1();
    void setDisplayValue2(String displayValue2);
    String getDisplayValue2();
    void setDisplayOrder1(int displayOrder1);
    int getDisplayOrder1();
    void setDisplayOrder2(int displayOrder2);
    int getDisplayOrder2();

    GenericCode createGenericCode();

    void init();

    GenericCode setGenericCode(String category, String cd);

    void setFieldsByMapFromDataSource(Map<String, Object> map);
    void setFieldsByMapFromApi(Map<String, Object> map);
}
