package com.healthy.FoodEase.services.impl;

import com.healthy.FoodEase.models.User;
import com.healthy.FoodEase.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String authority = "ROLE_" + user.getRole().name();
        System.out.println("[UserDetailsServiceImpl] Email: " + user.getEmail() + ", Role: " + user.getRole() + ", Authority: " + authority);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authority)
                .build();
    }
}
