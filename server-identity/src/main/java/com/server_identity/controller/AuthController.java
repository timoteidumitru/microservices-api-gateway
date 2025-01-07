package com.server_identity.controller;

import com.server_identity.dao.AuthRequest;
import com.server_identity.entity.UserCredentials;
import com.server_identity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public UserCredentials addNewUser(@RequestBody UserCredentials user) {
        return authService.addUser(user);
    }

    @PostMapping("/generate-token")
    public String getToken(@RequestBody AuthRequest authRequest) throws NoSuchAlgorithmException {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return authService.generateToken(authRequest.getName());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate-token")
    public String validateToken(@RequestParam String token) throws NoSuchAlgorithmException {
        authService.validateToken(token);
        return "Token is valid";
    }
}
