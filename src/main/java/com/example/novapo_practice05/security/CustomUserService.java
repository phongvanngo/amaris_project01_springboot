package com.example.novapo_practice05.security;

import com.example.novapo_practice05.domain.CustomUserDetails;
import com.example.novapo_practice05.domain.Role;
import com.example.novapo_practice05.domain.UserEntity;
import com.example.novapo_practice05.repository.RoleRepository;
import com.example.novapo_practice05.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("load user by username " + username);

        Optional<UserEntity> optionalUser = userRepository.findByEmail(username);

        System.out.println(optionalUser.get());

        UserEntity user = optionalUser.orElseThrow(
            () -> new UsernameNotFoundException("\"User details not found for the user : \" + username"));

        List<Role> roles = roleRepository.findRolesByUserID(user.getId());

        List<SimpleGrantedAuthority> userRoles = new ArrayList<>();
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            userRoles.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new CustomUserDetails(user, authorities,userRoles);
    }
}
