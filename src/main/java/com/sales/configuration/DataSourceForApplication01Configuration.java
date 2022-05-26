package com.sales.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Component
@Configuration
public class DataSourceForApplication01Configuration {
    @Value("${spring.datasource.application01.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.application01.url}")
    private String url;
    @Value("${spring.datasource.application01.username}")
    private String username;
    @Value("${spring.datasource.application01.password}")
    private String password;

    @Bean("applds01")
    public DataSource createDataSource() {
        return DataSourceBuilder
                .create()
                .driverClassName(driverClassName)
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

    @Bean("appljdbc01")
    public JdbcTemplate createJdbcTemplate(@Qualifier("applds01") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("applNpjdbc01")
    public NamedParameterJdbcTemplate createNamedParameterJdbcTemplate(@Qualifier("applds01") DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean("applTransactionManager")
    public PlatformTransactionManager createPlatformTransactionManager(@Qualifier("applds01") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
