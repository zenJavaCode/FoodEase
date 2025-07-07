package com.healthy.FoodEase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class FoodEaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodEaseApplication.class, args);
	}

}
