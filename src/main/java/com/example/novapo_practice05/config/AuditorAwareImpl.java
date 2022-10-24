package com.example.novapo_practice05.config;

import java.util.Optional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableJpaAuditing
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        System.out.println(user);
        return Optional.of(user);
    }
}
