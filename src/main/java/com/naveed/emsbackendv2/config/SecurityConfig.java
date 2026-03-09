package com.naveed.emsbackendv2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // پوسٹ مین سے ڈیٹا بھیجنے کے لیے CSRF کو بند رکھا ہے
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // 🔒 جادو کی لائن: اب کوئی بھی ریکویسٹ بغیر لاگ ان (Authentication) کے پاس نہیں ہوگی
                        .anyRequest().authenticated()
                )
                // اسپرنگ بوٹ کو بتایا جا رہا ہے کہ چابی (JWT Token) Keycloak سے چیک کرنی ہے
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> {})
                );

        return http.build();
    }
}