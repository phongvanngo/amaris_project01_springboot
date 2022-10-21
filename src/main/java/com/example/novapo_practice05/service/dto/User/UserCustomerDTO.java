package com.example.novapo_practice05.service.dto.User;

import com.example.novapo_practice05.domain.UserRole;
import java.util.Set;
import lombok.Data;
import org.springframework.security.core.userdetails.User;

@Data
public class UserCustomerDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String accountNumber;
    private Set<UserRole> roles;
}
