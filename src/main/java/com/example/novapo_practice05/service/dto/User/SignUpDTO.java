package com.example.novapo_practice05.service.dto.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpDTO {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String password;

    private String accountNumber;


    @Override
    public String toString() {
        return "SignUpDTO{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", password='" + password + '\'' +
            ", accountNumber='" + accountNumber + '\'' +
            '}';
    }
}
