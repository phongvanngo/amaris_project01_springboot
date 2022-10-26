package com.example.novapo_practice05.service.dto.User;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Data
@AllArgsConstructor
public class AuthResponseDTO {

    private String email;
    private Collection<SimpleGrantedAuthority> roles;
    private Collection<GrantedAuthority> authorities;
    private String accessToken;
}
