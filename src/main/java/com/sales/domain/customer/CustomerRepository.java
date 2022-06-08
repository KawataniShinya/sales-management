package com.sales.domain.customer;

import java.util.List;
import java.util.Map;

public interface CustomerRepository {
    List<Map<String, Object>> findUserByUserIdInExpiration(Customer customer);
}
