package com.example.springboot_training.controller;

import com.example.springboot_training.model.User;
import com.example.springboot_training.service.JwtService;
import com.example.springboot_training.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.example.springboot_training.dto.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthController(UserService userService,
            AuthenticationManager authenticationManager,
            JwtService jwtService) {

        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        return jwtService.generateToken(request.getUsername());
    }
}