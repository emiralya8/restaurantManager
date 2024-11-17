package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuItem;

import java.util.List;

public interface MenuItemService {
    List<MenuItem> getAllMenuItems();
    MenuItem createMenuItems(MenuItem menuItem);
    MenuItem getMenuItemsById(String id);
    MenuItem updateMenuItems(String id, MenuItem menuItemDetails);
    boolean deleteMenuItems(String id);
}
