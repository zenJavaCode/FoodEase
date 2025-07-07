package com.healthy.FoodEase.dtos;

import com.healthy.FoodEase.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String phone;
    private String password;
    private Role role; // ✅ Add this field
}
