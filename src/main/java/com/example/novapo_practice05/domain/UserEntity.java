package com.example.novapo_practice05.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity extends AbstractAuditingEntity {

    @Id
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Email
    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name = "account_number")
    private String accountNumber;

//    private Set<Authority> authorities;
//
//    private Set<UserRole> roles;


//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//    private Set<Authority> authorities;
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//    private Set<UserRole> roles;



}

