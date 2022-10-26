package com.example.novapo_practice05.service;

import com.example.novapo_practice05.domain.CustomUserDetails;
import com.example.novapo_practice05.domain.UserEntity;
import com.example.novapo_practice05.repository.RoleRepository;
import com.example.novapo_practice05.repository.UserRepository;
import com.example.novapo_practice05.repository.UsersRolesRepository;
import com.example.novapo_practice05.security.JwtUtils;
import com.example.novapo_practice05.service.dto.User.AuthDTO;
import com.example.novapo_practice05.service.dto.User.AuthResponseDTO;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UsersRolesRepository usersRolesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserService userService;

    public AuthResponseDTO grantUser(AuthDTO authDTO) {

        // spring security auto authenticate
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authDTO.getEmail(),
                authDTO.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String jwt = jwtUtils.generateAccessToken(customUserDetails);
        return new AuthResponseDTO(authDTO.getEmail(), customUserDetails.getRoles(),customUserDetails.getAuthorities(), jwt);

    }

    public AuthResponseDTO grantUser2(AuthDTO authDTO) {

        Optional<UserEntity> user = userService.getByEmail(authDTO.getEmail());

        if (user.isEmpty()) {
            throw new BadCredentialsException("No user registered with this details!");
        }

        if (!passwordEncoder.matches(authDTO.getPassword(), user.get().getPassword())) {
            throw new BadCredentialsException("Invalid password!");
        }

        // why to do this bulk of code
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authDTO.getEmail(),
                authDTO.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails customUserDetails = null;

        String jwt = jwtUtils.generateAccessToken(customUserDetails);

        return null;
    }


}
