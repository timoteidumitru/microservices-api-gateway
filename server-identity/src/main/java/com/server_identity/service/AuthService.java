package com.server_identity.service;

import com.server_identity.entity.UserCredentials;
import com.server_identity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public UserCredentials addUser(UserCredentials user) {
        // Validate input (optional, depending on your needs)
        if (user == null || user.getName() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("User credentials must not be null and should include username and password.");
        }

        // Check for duplicate username (if required)
        if (userRepository.existsByName(user.getName())) {
            throw new IllegalStateException("Username is already taken.");
        }

        // Save the user and return the persisted entity
        return userRepository.save(user);
    }


}
