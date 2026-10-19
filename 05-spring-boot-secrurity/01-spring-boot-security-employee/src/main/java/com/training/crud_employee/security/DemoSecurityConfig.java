package com.training.crud_employee.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
    InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles(UserRole.EMPLOYEE.getRole())
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles(UserRole.EMPLOYEE.getRole(), UserRole.MANAGER.getRole())
                .build();

        UserDetails joseph = User.builder()
                .username("joseph")
                .password("{noop}test123")
                .roles(UserRole.EMPLOYEE.getRole(), UserRole.MANAGER.getRole(), UserRole.ADMIN.getRole())
                .build();

        return new InMemoryUserDetailsManager(john, mary, joseph);
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
