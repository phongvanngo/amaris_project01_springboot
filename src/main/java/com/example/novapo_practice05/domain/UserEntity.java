package com.example.novapo_practice05.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
@Table(name = "users")
public class UserEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToMany
    @JoinTable(
        name = "users_authorities",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Authority> authorities;

    @ManyToMany
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<UserRole> roles;


//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//    private Set<Authority> authorities;
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//    private Set<UserRole> roles;



}

