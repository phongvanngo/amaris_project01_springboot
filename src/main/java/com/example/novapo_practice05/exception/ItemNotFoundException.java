package com.example.novapo_practice05.exception;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(Long id) {
        super(String.format("Item with Id %d not found", id));
    }
}
