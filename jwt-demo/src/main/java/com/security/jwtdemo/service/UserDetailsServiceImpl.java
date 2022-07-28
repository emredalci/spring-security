package com.security.jwtdemo.service;

import com.security.jwtdemo.entity.User;
import com.security.jwtdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username){
        final User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found : " + username));

        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .accountLocked(false)
            .accountExpired(false)
            .credentialsExpired(false)
            .disabled(false)
            .build();
    }
}
