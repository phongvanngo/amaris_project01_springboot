package com.example.novapo_practice05.service.dto.User;

import java.util.Set;

import com.example.novapo_practice05.domain.Role;

import lombok.Data;

@Data
public class UserCustomerDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String accountNumber;
    private Set<Role> roles;
}
