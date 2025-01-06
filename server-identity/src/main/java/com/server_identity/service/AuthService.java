package com.server_identity.service;

import com.server_identity.entity.UserCredentials;
import com.server_identity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

        // Check for duplicate username (if required)
        if (userRepository.existsByName(user.getName())) {
            throw new IllegalStateException("Username is already taken.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user and return the persisted entity
        return userRepository.save(user);
    }

    public String generateToken(String userName){
        return jwtService.generateToken(userName);
    }

    public Boolean validateToken(String token, UserDetails user){
        return jwtService.validateToken(token, user);
    }


}
