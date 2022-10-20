package com.example.novapo_practice05.config;

import com.example.novapo_practice05.filter.AuthoritiesLoggingAfterFilter;
import com.example.novapo_practice05.filter.AuthoritiesLoggingAtFilter;
import com.example.novapo_practice05.filter.RequestValidationBeforeFilter;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
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

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .cors().configurationSource(corsConfigurationSource);
        http.csrf().disable();

        http.addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
            .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
            .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
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
