package com.healthy.FoodEase.services.impl;

import com.healthy.FoodEase.models.MenuItem;
import com.healthy.FoodEase.repositories.MenuItemRepository;
import com.healthy.FoodEase.services.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository repository;

    @Override
    public MenuItem create(MenuItem item) {
        return repository.save(item);
    }

    @Override
    public List<MenuItem> getAll() {
        return repository.findAll();
    }

    @Override
    public MenuItem update(Long id, MenuItem updatedItem) {
        MenuItem existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        existing.setName(updatedItem.getName());
        existing.setDescription(updatedItem.getDescription());
        existing.setPrice(updatedItem.getPrice());
        existing.setAvailable(updatedItem.isAvailable());
        existing.setImageUrl(updatedItem.getImageUrl());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

