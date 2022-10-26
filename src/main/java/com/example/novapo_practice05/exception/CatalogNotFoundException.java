package com.example.novapo_practice05.exception;

public class CatalogNotFoundException extends RuntimeException{
    public CatalogNotFoundException(Long id) {
        super(String.format("Catalog with Id %d not found", id));
    }
}
