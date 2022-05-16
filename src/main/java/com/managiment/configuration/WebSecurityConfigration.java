package com.managiment.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@PropertySource(value = "classpath:properties/sql.properties")
public class WebSecurityConfigration extends WebSecurityConfigurerAdapter {

    @Value("${SQLA003}")
    String SQL_USERSBYUSERNAME;
    @Value("${SQLA004}")
    String SQL_AUTHBYUSERNAME;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/js/**").permitAll() //webjarsへアクセス許可
                .antMatchers("/css/**").permitAll() //cssへアクセス許可
                .antMatchers("/images/**").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginProcessingUrl("/menu")
                .loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/menu", true)
                .usernameParameter("id")
                .passwordParameter("pass")
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
        ;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth, @Qualifier("applds01") DataSource dataSource) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(SQL_USERSBYUSERNAME)
                .authoritiesByUsernameQuery(SQL_AUTHBYUSERNAME);
    }
}
