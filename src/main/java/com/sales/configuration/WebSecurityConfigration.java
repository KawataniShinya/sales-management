package com.sales.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@PropertySource(value = "classpath:properties/sql.properties")
public class WebSecurityConfigration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfigration(UserDetailsService userDetailsService) {
        super();
        this.userDetailsService = userDetailsService;
    }

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
                .loginPage("/login")
                .usernameParameter("id")
                .passwordParameter("password")
                .loginProcessingUrl("/authentication")
                .defaultSuccessUrl("/menu", true)
                .failureUrl("/login?error")
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
        ;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth, @Qualifier("applds01") DataSource dataSource) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
