package com.example.novapo_practice05.web.rest;

import com.example.novapo_practice05.service.UserService;
import com.example.novapo_practice05.service.dto.User.SignUpDTO;
import com.example.novapo_practice05.service.dto.User.UserResponseDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

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
}
