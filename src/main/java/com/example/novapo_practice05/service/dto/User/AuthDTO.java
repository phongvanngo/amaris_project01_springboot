package com.example.novapo_practice05.service.dto.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class AuthDTO {

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;
}
