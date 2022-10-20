package com.example.novapo_practice05.config;

import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        CorsConfigurationSource corsConfigurationSource = request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
            config.setAllowedMethods(Collections.singletonList("*"));
            config.setAllowCredentials(true);
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setMaxAge(3600L);
            return config;
        };

        http.cors().configurationSource(corsConfigurationSource)
            .and().csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/item").hasRole("CUSTOMER")
            .antMatchers("/api/catalog").hasAnyRole("ADMIN")
            .antMatchers("/api/cart").hasRole("CUSTOMER")
            .antMatchers("/api/users").authenticated()
//            .antMatchers("/api/catalog", "/api/public/*", "/api/signup", "/api/register").permitAll()
            .and().formLogin()
            .and().httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
