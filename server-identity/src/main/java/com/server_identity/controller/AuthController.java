package com.server_identity.controller;

import com.server_identity.entity.UserCredentials;
import com.server_identity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
