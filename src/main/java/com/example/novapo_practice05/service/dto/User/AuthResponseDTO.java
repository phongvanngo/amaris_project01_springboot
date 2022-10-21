package com.example.novapo_practice05.service.dto.User;

import com.example.novapo_practice05.domain.UserRole;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDTO {
    private String email;
    private Set<UserRole> roles;
    private String accessToken;
}
