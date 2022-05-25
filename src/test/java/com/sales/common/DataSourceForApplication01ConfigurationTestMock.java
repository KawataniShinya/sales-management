package com.sales.common;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

public class DataSourceForApplication01ConfigurationTestMock {
    @Value("${spring.datasource.application01.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.application01.url}")
    private String url;
    @Value("${spring.datasource.application01.username}")
    private String username;
    @Value("${spring.datasource.application01.password}")
    private String password;

    @Bean("applds01TestMock")
    public DataSource createDataSource() {
        return new TransactionAwareDataSourceProxy(
                DataSourceBuilder
                        .create()
                        .driverClassName(driverClassName)
                        .url(url)
                        .username(username)
                        .password(password)
                        .build());
    }

    @Bean("appljdbc01TestMock")
    public JdbcTemplate createJdbcTemplate(@Qualifier("applds01TestMock") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("applNpjdbc01TestMock")
    public NamedParameterJdbcTemplate createNamedParameterJdbcTemplate(@Qualifier("applds01TestMock") DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }

}
