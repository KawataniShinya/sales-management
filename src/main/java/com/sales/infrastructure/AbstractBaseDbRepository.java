package com.sales.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

abstract class AbstractBaseDbRepository {
    @Autowired
    @Qualifier("appljdbc01")
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("applNpjdbc01")
    protected NamedParameterJdbcTemplate npJdbcTemplate;
}
