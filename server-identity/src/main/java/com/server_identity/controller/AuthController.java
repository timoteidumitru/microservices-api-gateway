package com.server_identity.controller;

import com.server_identity.entity.UserCredentials;
import com.server_identity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserCredentials> registerUser(@RequestBody UserCredentials user) {
        UserCredentials savedUser = authService.addUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/generate-token")
    public ResponseEntity<String> generateToken(@RequestParam String userName) {
        String generatedToken = authService.generateToken(userName);
        return new ResponseEntity<>(generatedToken, HttpStatus.CREATED);
    }

    @PostMapping("/validate-token")
    public ResponseEntity<String> validateToken(@RequestBody String token, UserDetails user) {
        String validateToken = authService.validateToken(token, user);
        return new ResponseEntity<>(validateToken, HttpStatus.CREATED);
    }
}
