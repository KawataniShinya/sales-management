package com.sales.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Component
@Configuration
public class DataSourceForSystem01Configuration {
    private final String driverClassName;
    private final String url;
    private final String username;
    private final String password;

    @Autowired
    public DataSourceForSystem01Configuration(
            @Value("${spring.datasource.system01.driverClassName}") String driverClassName,
            @Value("${spring.datasource.system01.url}") String url,
            @Value("${spring.datasource.system01.username}") String username,
            @Value("${spring.datasource.system01.password}") String password) {
        this.driverClassName = driverClassName;
        this.url = url;
        this.username = username;
        this.password = password;
    }

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

    @Bean("sysTransactionManager")
    @Primary
    public PlatformTransactionManager createPlatformTransactionManager(@Qualifier("sysds01") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
