package com.example.novapo_practice05.service;

import com.example.novapo_practice05.domain.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenService {
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    public String generateAccessToken(UserEntity user) {
        return Jwts.builder()
            .setSubject(String.format("%s,%s", user.getId(), user.getEmail()))
            .setIssuer("CodeJava")
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            .compact();

    }
}
