package com.example.novapo_practice05.service.dto.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;
}
