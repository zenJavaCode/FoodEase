package com.healthy.FoodEase.controllers;

import com.healthy.FoodEase.models.MenuItem;
import com.healthy.FoodEase.services.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService service;

    @GetMapping
    public List<MenuItem> getAll() {
        return service.getAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MenuItem> create(@RequestBody MenuItem item) {
        return ResponseEntity.ok(service.create(item));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MenuItem> update(@PathVariable Long id, @RequestBody MenuItem item) {
        return ResponseEntity.ok(service.update(id, item));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/admin-only")
    @PreAuthorize("hasRole('ADMIN')") // You can also try hasAuthority("ROLE_ADMIN")
    public ResponseEntity<String> adminEndpoint() {
        System.out.println("[AdminController] Admin endpoint hit!");
        return ResponseEntity.ok("You are an ADMIN!");
    }
}

