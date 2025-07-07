package com.healthy.FoodEase.services;

import com.healthy.FoodEase.models.MenuItem;

import java.util.List;

public interface MenuItemService {
    MenuItem create(MenuItem item);
    List<MenuItem> getAll();
    MenuItem update(Long id, MenuItem updatedItem);
    void delete(Long id);
}
