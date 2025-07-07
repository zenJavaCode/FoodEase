package com.healthy.FoodEase.repositories;

import com.healthy.FoodEase.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);  // for login/auth
    boolean existsByEmail(String email);       // for validation
    boolean existsByPhone(String phone);
}
