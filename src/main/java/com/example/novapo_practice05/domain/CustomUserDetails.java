package com.example.novapo_practice05.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import java.util.List;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CustomUserDetails implements UserDetails {

    UserEntity user;

    Collection<GrantedAuthority> authorities;

    Collection<SimpleGrantedAuthority> roles;

    public CustomUserDetails(UserEntity user,List<GrantedAuthority> authorities,List<SimpleGrantedAuthority> roles) {
        this.user = user;
        this.authorities = authorities;
        this.roles = roles;
    }

    public CustomUserDetails(User user) {
        this.user = new UserEntity();
        this.user.setEmail(user.getUsername());
        this.user.setPassword(user.getPassword());
        this.authorities = user.getAuthorities();

    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Collection<SimpleGrantedAuthority> getRoles() {
        return roles;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}