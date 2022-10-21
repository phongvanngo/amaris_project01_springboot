package com.example.novapo_practice05.service.dto.User;

import com.example.novapo_practice05.domain.UserRole;
import java.time.Instant;
import java.util.Set;
import lombok.Data;

@Data
public class UserResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;    private String email;
    private String accountNumber;
    private Set<UserRole> roles;
    private Instant createdAt;

}
