package com.example.springboot_training.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private final String secretKey = "mySecretKeyForSpringBootTrainingProject123456";

    public String generateToken(String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(
                        io.jsonwebtoken.security.Keys.hmacShaKeyFor(
                                secretKey.getBytes()))
                .compact();
    }

    public String extractUsername(String token) {

        return Jwts.parser()
                .verifyWith(
                        io.jsonwebtoken.security.Keys.hmacShaKeyFor(
                                secretKey.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token, String username) {

        String extractedUsername = extractUsername(token);

        return extractedUsername.equals(username);
    }
}