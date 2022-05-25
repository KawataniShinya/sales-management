package com.sales.infrastructure;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

abstract class AbstractBaseSystemDbRepository {
    protected JdbcTemplate jdbcTemplate;
    protected NamedParameterJdbcTemplate npJdbcTemplate;

    public AbstractBaseSystemDbRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.npJdbcTemplate = npJdbcTemplate;
    }
}
