package com.managiment.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Configuration
public class SecondaryConfiguration {
    @Value("${spring.datasource.secondary.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.secondary.url}")
    private String url;
    @Value("${spring.datasource.secondary.username}")
    private String username;
    @Value("${spring.datasource.secondary.password}")
    private String password;

    @Bean("secondaryds")
    public DataSource createDataSource() {
        return DataSourceBuilder
                .create()
                .driverClassName(driverClassName)
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

    @Bean("secondaryjdbc")
    public JdbcTemplate createJdbcTemplate(@Qualifier("secondaryds") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("secondaryNpjdbc")
    public NamedParameterJdbcTemplate createNamedParameterJdbcTemplate(@Qualifier("secondaryds") DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }

}
