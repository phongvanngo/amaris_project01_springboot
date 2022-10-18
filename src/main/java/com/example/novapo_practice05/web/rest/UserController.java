package com.example.novapo_practice05.web.rest;

import com.example.novapo_practice05.service.UserService;
import com.example.novapo_practice05.service.dto.User.SignUpDTO;
import com.example.novapo_practice05.service.dto.User.UserResponseDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/users")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}
