package com.example.novapo_practice05.web.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.novapo_practice05.service.UserService;
import com.example.novapo_practice05.service.dto.User.SetRoleDTO;
import com.example.novapo_practice05.service.dto.User.UserResponseDTO;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/ping")
    public String ping() {
        return "hello";
    }

    @PostMapping("/set-role")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<UserResponseDTO> setRole(@RequestBody SetRoleDTO setRoleDTO) {
        System.out.println(setRoleDTO);

        return new ResponseEntity<>(userService.setRole(setRoleDTO), HttpStatus.OK);
    }

    @GetMapping("/users")
    @RolesAllowed("ROLE_ADMIN")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

}
