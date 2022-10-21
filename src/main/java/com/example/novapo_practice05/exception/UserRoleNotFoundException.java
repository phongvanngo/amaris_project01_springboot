package com.example.novapo_practice05.exception;

public class UserRoleNotFoundException extends RuntimeException{
    public UserRoleNotFoundException(String role) {
        super(String.format("Role %s not found", role));
    }
}
