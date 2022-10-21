package com.example.novapo_practice05.service.dto.User;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String email;
    private String accessToken;

    public AuthResponseDTO() { }

    public AuthResponseDTO(String email, String accessToken) {
        this.email = email;
        this.accessToken = accessToken;
    }
}
