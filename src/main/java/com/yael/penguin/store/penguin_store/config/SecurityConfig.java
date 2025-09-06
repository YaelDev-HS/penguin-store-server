package com.yael.penguin.store.penguin_store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.yael.penguin.store.penguin_store.application.services.AuthFilterService;




@Configuration
public class SecurityConfig {

    @Autowired
    private AuthFilterService authFilterService;


    @Bean
    PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .authorizeHttpRequests(c -> c
                .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(authFilterService, UsernamePasswordAuthenticationFilter.class)
            .csrf(c -> c.disable());

        return http.build();
    }


}