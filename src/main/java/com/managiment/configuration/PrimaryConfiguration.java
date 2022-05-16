package com.managiment.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Configuration
public class PrimaryConfiguration {
    @Value("${spring.datasource.primary.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.primary.url}")
    private String url;
    @Value("${spring.datasource.primary.username}")
    private String username;
    @Value("${spring.datasource.primary.password}")
    private String password;

    @Bean
    @Primary
    public DataSource createDataSource() {
        return DataSourceBuilder
                .create()
                .driverClassName(driverClassName)
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

    @Bean
    @Primary
    public JdbcTemplate createJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Primary
    public NamedParameterJdbcTemplate createNamedParameterJdbcTemplate(DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }

}
