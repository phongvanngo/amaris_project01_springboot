package com.example.novapo_practice05.web.rest;

import com.example.novapo_practice05.domain.CustomUserDetails;
import com.example.novapo_practice05.domain.UserEntity;
import com.example.novapo_practice05.service.JwtTokenProvider;
import com.example.novapo_practice05.service.JwtTokenService;
import com.example.novapo_practice05.service.UserService;
import com.example.novapo_practice05.service.dto.User.AuthDTO;
import com.example.novapo_practice05.service.dto.User.AuthResponseDTO;
import com.example.novapo_practice05.service.dto.User.SetRoleDTO;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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



    @PostMapping("/register/admin")
    public ResponseEntity<UserResponseDTO> registerAdmin(@RequestBody SignUpDTO request) {
//        return request.toString();
        System.out.println(request);
        return new ResponseEntity<>(userService.registerAdminUser(request), HttpStatus.OK);
    }

    @PostMapping("/set-role")
    public ResponseEntity<UserResponseDTO> setRole(@RequestBody SetRoleDTO setRoleDTO) {
//        return request.toString();
        System.out.println(setRoleDTO);

        return new ResponseEntity<>(userService.setRole(setRoleDTO), HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }



}
