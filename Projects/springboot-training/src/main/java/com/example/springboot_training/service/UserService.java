package com.example.springboot_training.service;

import com.example.springboot_training.model.User;
import com.example.springboot_training.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user) {

        String hashedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }
}