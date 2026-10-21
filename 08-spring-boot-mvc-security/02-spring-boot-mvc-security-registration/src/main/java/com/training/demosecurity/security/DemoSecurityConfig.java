package com.training.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.training.demosecurity.constant.UserRole;
import com.training.demosecurity.service.UserService;

@Configuration
public class DemoSecurityConfig {

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider(userService);
        auth.setPasswordEncoder(passwordEncoder());

        return auth;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity, AuthenticationSuccessHandler customAuthenticationSuccessHandler) throws Exception {
        httpSecurity.authorizeHttpRequests(
                configurer -> configurer
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/registration/**").permitAll()
                        .requestMatchers("/employees/**").hasRole(UserRole.EMPLOYEE.getAuthority())
                        .requestMatchers("/managers/**").hasRole(UserRole.MANAGER.getAuthority())
                        .requestMatchers("/systems/**").hasRole(UserRole.ADMIN.getAuthority())
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/showLoginPage")
                        .loginProcessingUrl("/authenticateUser")
                        .successHandler(customAuthenticationSuccessHandler)
                        .permitAll())
                .logout(logout -> logout.permitAll())
                .exceptionHandling(configurer -> configurer
                        .accessDeniedPage("/access-denied"));

        return httpSecurity.build();
    }
}
