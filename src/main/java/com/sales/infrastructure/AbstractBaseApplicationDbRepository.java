package com.sales.infrastructure;

import com.sales.common.LoggingDelegateRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

abstract class AbstractBaseApplicationDbRepository {
    protected final JdbcTemplate jdbcTemplate;
    protected final NamedParameterJdbcTemplate npJdbcTemplate;
    protected final LoggingDelegateRepository loggingDelegateRepository;

    public AbstractBaseApplicationDbRepository(
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate npJdbcTemplate,
            LoggingDelegateRepository loggingDelegateRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.npJdbcTemplate = npJdbcTemplate;
        this.loggingDelegateRepository = loggingDelegateRepository;
    }
}
