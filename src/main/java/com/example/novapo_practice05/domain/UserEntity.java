package com.example.novapo_practice05.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class UserEntity extends AbstractAuditingEntity {

    public enum UserRole {
        CUSTOMER,ADMINISTRATOR
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Email
    @NotNull
    @Column(name="email")
    private String email;

    @NotNull
    @Column(name="phone_number")
    private String phoneNumber;

    @NotNull
    @Column(name="password")
    private String password;

    @Column(name="account_number")
    private String accountNumber;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="role")
    private UserRole role = UserRole.CUSTOMER;


}

