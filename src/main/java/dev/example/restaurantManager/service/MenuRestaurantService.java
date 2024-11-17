package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuRestaurant;
import java.util.List;

public interface MenuRestaurantService {
    List<MenuRestaurant> getAllMenuRestaurants();
    MenuRestaurant createMenuRestaurants(MenuRestaurant menuRestaurant);
    MenuRestaurant getMenuRestaurantsById(String id);
    MenuRestaurant updateMenuRestaurants(String id, MenuRestaurant menuRestaurantDetails);
    boolean deleteMenuRestaurants(String id);
    long countMenuRestaurant();
}
