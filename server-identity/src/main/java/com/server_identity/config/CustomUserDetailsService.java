package com.server_identity.config;

import com.server_identity.entity.UserCredentials;
import com.server_identity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredentials> credentials = userRepository.findByName(username);
        return credentials.map(CustomUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("User not found! " + username));
    }
}
