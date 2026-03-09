package com.naveed.emsbackendv2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

// یہ اینوٹیشنز بتاتی ہیں کہ یہ کلاس پروجیکٹ کی سیکیورٹی سنبھالے گی
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // پوسٹ مین سے ڈیٹا بھیجنے کے لیے CSRF کو بند کرنا ضروری ہے
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // یہ لائن عارضی طور پر ہمارے تمام APIs کے دروازے کھول رہی ہے
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}