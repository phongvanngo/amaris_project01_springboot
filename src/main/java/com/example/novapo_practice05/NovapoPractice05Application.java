package com.example.novapo_practice05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity(debug = true)
public class NovapoPractice05Application {

	public static void main(String[] args) {
		SpringApplication.run(NovapoPractice05Application.class, args);
	}

}
