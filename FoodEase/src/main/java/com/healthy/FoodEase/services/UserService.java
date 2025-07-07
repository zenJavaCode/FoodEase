package com.healthy.FoodEase.services;

import com.healthy.FoodEase.dtos.RegisterRequest;
import com.healthy.FoodEase.enums.Role;
import com.healthy.FoodEase.models.User;
import com.healthy.FoodEase.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already in use";
        }

        if (userRepository.existsByPhone(request.getPhone())) {
            return "Phone already in use";
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // ✅ Assign role from request or default to USER
        Role selectedRole = request.getRole() != null ? request.getRole() : Role.USER;
        user.setRole(selectedRole);

        userRepository.save(user);

        return "User registered successfully with role: " + selectedRole;
    }
}