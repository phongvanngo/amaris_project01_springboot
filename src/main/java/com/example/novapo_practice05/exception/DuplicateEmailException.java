package com.example.novapo_practice05.exception;

public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(String email) {
        super(String.format("User with email %s already exists", email));
    }
}
