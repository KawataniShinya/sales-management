package com.sales.infrastructure;

import com.sales.common.ApplicationLogConstant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

abstract class AbstractBaseSystemDbRepository {
    protected JdbcTemplate jdbcTemplate;
    protected NamedParameterJdbcTemplate npJdbcTemplate;

    public AbstractBaseSystemDbRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.npJdbcTemplate = npJdbcTemplate;
    }
}
