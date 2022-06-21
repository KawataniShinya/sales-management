package com.sales.domain.genericcode;

import java.util.List;
import java.util.Map;

public interface GenericCodeRepository {
    List<Map<String, Object>> findGenericCodeByCategoryOrder1(GenericCodeDomainService genericCodeDomainService);
}
