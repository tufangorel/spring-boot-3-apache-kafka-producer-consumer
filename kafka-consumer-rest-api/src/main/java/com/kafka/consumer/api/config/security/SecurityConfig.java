package com.kafka.consumer.api.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http
                .requiresChannel(channel ->
                        channel.anyRequest().requiresSecure())
                .authorizeRequests(authorize ->
                        authorize.anyRequest().permitAll())
                .csrf().disable()
                .build();
    }

}