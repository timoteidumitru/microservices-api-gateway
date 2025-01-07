package com.server_identity.service;

import com.server_identity.entity.UserCredentials;
import com.server_identity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    public UserCredentials addUser(UserCredentials user) {
        // Validate input
        if (user == null || user.getName() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("User credentials must not be null and should include username and password.");
        }

        // Check for duplicate username
        if (userRepository.existsByName(user.getName())) {
            throw new IllegalStateException("Username is already taken.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public String generateToken(String username) throws NoSuchAlgorithmException {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) throws NoSuchAlgorithmException {
        jwtService.validateToken(token);
    }

}
