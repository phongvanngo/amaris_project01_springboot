package com.example.novapo_practice05.web.rest;

import com.example.novapo_practice05.service.AuthService;
import com.example.novapo_practice05.service.UserService;
import com.example.novapo_practice05.service.dto.User.AuthDTO;
import com.example.novapo_practice05.service.dto.User.AuthResponseDTO;
import com.example.novapo_practice05.service.dto.User.SignUpDTO;
import com.example.novapo_practice05.service.dto.User.UserResponseDTO;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody SignUpDTO request) {
        return new ResponseEntity<>(userService.registerUser(request), HttpStatus.OK);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthDTO request) {
        System.out.println("request: " + request);
        AuthResponseDTO response = authService.grantUser(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
