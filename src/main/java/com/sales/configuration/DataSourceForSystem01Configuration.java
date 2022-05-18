package com.sales.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
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
public class DataSourceForSystem01Configuration {
    @Value("${spring.datasource.system01.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.system01.url}")
    private String url;
    @Value("${spring.datasource.system01.username}")
    private String username;
    @Value("${spring.datasource.system01.password}")
    private String password;

    @Bean("sysds01")
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

    @Bean("sysjdbc01")
    @Primary
    public JdbcTemplate createJdbcTemplate(@Qualifier("sysds01") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("sysNpjdbc01")
    @Primary
    public NamedParameterJdbcTemplate createNamedParameterJdbcTemplate(@Qualifier("sysds01") DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }

}
