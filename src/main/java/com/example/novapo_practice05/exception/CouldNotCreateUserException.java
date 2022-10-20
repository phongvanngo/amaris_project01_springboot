package com.example.novapo_practice05.exception;

public class CouldNotCreateUserException extends RuntimeException{
    public CouldNotCreateUserException(String username) {
        super(String.format("Cannot create user with username =", username));
    }
}
