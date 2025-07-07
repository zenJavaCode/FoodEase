package com.healthy.FoodEase.dtos;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
