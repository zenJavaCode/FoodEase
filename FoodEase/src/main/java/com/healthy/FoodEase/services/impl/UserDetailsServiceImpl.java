package com.healthy.FoodEase.services.impl;

import com.healthy.FoodEase.models.User;
import com.healthy.FoodEase.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("[UserDetailsServiceImpl] Loading user by email: {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.warn("[UserDetailsServiceImpl] User not found with email: {}", email);
                    return new UsernameNotFoundException("User not found");
                });

        String authority = "ROLE_" + user.getRole().name();
        logger.info("[UserDetailsServiceImpl] Found user: {} | Role: {} | Authority: {}", user.getEmail(), user.getRole(), authority);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authority)
                .build();
    }
}
