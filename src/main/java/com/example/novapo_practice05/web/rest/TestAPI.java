package com.example.novapo_practice05.web.rest;

import com.example.novapo_practice05.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestAPI {

    private final UserRepository userRepository;

    public TestAPI(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String testAPI() {
        return this.userRepository.generateUserId();
    }
}
