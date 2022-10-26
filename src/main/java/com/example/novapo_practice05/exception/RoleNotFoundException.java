package com.example.novapo_practice05.exception;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException(String role) {
        super(String.format("Role %s not found", role));
    }
}
