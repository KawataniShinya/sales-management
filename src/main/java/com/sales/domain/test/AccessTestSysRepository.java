package com.sales.domain.test;

import java.util.List;
import java.util.Map;

public interface AccessTestSysRepository {
    public List<Map<String, Object>> selectAll();
    public List<Map<String, Object>> selectLimit();
    public void insertUser();
}
