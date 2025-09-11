//package com.betacom.jpa.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf -> csrf.disable()) // Disabilita CSRF per le API REST
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/rest/**").permitAll() // Tutte le API rest libere
//                .anyRequest().authenticated() // il resto richiede autenticazione
//            );
//
//        return http.build();
//    }
//}
