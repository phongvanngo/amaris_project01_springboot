package com.example.novapo_practice05.web.rest;

import com.example.novapo_practice05.domain.UserEntity;
import com.example.novapo_practice05.service.JwtTokenService;
import com.example.novapo_practice05.service.UserService;
import com.example.novapo_practice05.service.dto.User.AuthDTO;
import com.example.novapo_practice05.service.dto.User.AuthResponseDTO;
import com.example.novapo_practice05.service.dto.User.SignUpDTO;
import com.example.novapo_practice05.service.dto.User.UserResponseDTO;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenService jwtUtilService;
    @Autowired
    UserService userService;

    @GetMapping("/ping")
    public String ping() {
        return "hello";
    }

    @PostMapping("/signup")
    public UserResponseDTO createUser(@RequestBody SignUpDTO request) {
//        return request.toString();
        return userService.createUser(request);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody SignUpDTO request) {
//        return request.toString();
        return new ResponseEntity<>(userService.registerUser(request), HttpStatus.OK);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<UserResponseDTO> egisterAdmin(@RequestBody SignUpDTO request) {
//        return request.toString();
        return new ResponseEntity<>(userService.registerAdminUser(request), HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthDTO request) {
        try {
            System.out.println(request);

//            Authentication authentication = authManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                    request.getEmail(), request.getPassword())
//            );
//
//            System.out.println(authentication);
//
//            UserEntity user = (UserEntity) authentication.getPrincipal();
            UserEntity user = new UserEntity();
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            String accessToken = jwtUtilService.generateAccessToken(user);
            AuthResponseDTO response = new AuthResponseDTO(user.getEmail(), accessToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
