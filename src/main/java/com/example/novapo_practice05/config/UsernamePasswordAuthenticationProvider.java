package com.example.novapo_practice05.config;

import com.example.novapo_practice05.domain.Authority;
import com.example.novapo_practice05.domain.UserEntity;
import com.example.novapo_practice05.domain.UserEntity.UserRole;
import com.example.novapo_practice05.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Optional<UserEntity> user = userRepository.findByEmail(username);
        if (user.isPresent()) {
            if (passwordEncoder.matches(pwd, user.get().getPassword())) {
//                return new UsernamePasswordAuthenticationToken(username,pwd,getGrantedAuthorities(user.get().getAuthorities()));
                return new UsernamePasswordAuthenticationToken(username, pwd,
                    getGrantedAuthoritiesUsingRole(user.get().getRole()));
            } else {
                throw new BadCredentialsException("Invalid password");
            }
        } else {
            throw new BadCredentialsException("No user registered with this details");
        }

    }

    private List<GrantedAuthority> getGrantedAuthoritiesUsingRole(UserRole role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        String userRole ="ROLE_ADMIN";
        if (role==UserRole.CUSTOMER)  {
            userRole = "ROLE_CUSTOMER";
        }
        grantedAuthorities.add(new SimpleGrantedAuthority(userRole));
        return grantedAuthorities;
    }

    private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
