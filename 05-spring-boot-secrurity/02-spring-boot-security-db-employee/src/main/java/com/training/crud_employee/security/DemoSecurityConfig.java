package com.training.crud_employee.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

enum UserRole {
    EMPLOYEE("EMPLOYEE"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN");

    private String role;

    private UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}

@Configuration
public class DemoSecurityConfig {

    @Bean 
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
          "SELECT user_id, pw, active FROM members WHERE user_id=?"
        );

        // define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
            "SELECT user_id, role FROM roles WHERE user_id=?"
        );
        return jdbcUserDetailsManager;
    }

    @Bean
    SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(configurer -> 
            configurer
                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole(UserRole.EMPLOYEE.getRole())
                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole(UserRole.EMPLOYEE.getRole())
                .requestMatchers(HttpMethod.PATCH, "/api/employees/**").hasRole(UserRole.MANAGER.getRole())
                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole(UserRole.MANAGER.getRole())
                .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole(UserRole.MANAGER.getRole())
                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole(UserRole.ADMIN.getRole())
        );

        httpSecurity.httpBasic(Customizer.withDefaults());

        // disable Cross Site Request Forgery
        // not required for REST APIs
        httpSecurity.csrf(csrf -> csrf.disable());

        return httpSecurity.build();
    }
}
