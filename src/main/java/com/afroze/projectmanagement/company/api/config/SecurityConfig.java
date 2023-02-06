package com.afroze.projectmanagement.company.api.config;

import jakarta.ws.rs.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests(a -> a.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll())
                .authorizeHttpRequests(a -> a.requestMatchers("/actuator").permitAll())
                .authorizeHttpRequests(a -> a.requestMatchers("/actuator/**").permitAll())
                .authorizeHttpRequests(a -> a.requestMatchers("/swagger-ui.html").permitAll())
                .authorizeHttpRequests(a -> a.requestMatchers("/**").authenticated())
                .oauth2ResourceServer()
                .jwt();

        return http.build();
    }
}