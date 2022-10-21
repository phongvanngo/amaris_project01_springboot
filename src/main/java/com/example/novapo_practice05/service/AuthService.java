package com.example.novapo_practice05.service;

import com.example.novapo_practice05.domain.CustomUserDetails;
import com.example.novapo_practice05.domain.UserEntity;
import com.example.novapo_practice05.exception.UserNotFoundException;
import com.example.novapo_practice05.repository.UserRepository;
import com.example.novapo_practice05.security.JwtUtils;
import com.example.novapo_practice05.service.dto.User.AuthDTO;
import com.example.novapo_practice05.service.dto.User.AuthResponseDTO;
import com.example.novapo_practice05.service.dto.User.UserResponseDTO;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private JwtUtils jwtUtils;
    @Autowired
    private UserService userService;

    public AuthResponseDTO grantUser(AuthDTO authDTO) {

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

        CustomUserDetails customUserDetails = new CustomUserDetails(user.get());

        String jwt = jwtUtils.generateAccessToken(customUserDetails);

        return new AuthResponseDTO(user.get().getEmail(), user.get().getRoles(), jwt);
    }


}
