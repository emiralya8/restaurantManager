package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.EatInOrderRestaurant;
import dev.example.restaurantManager.model.Menu;
import dev.example.restaurantManager.model.TableRestaurant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface EatInOrderRestaurantService {
    List<EatInOrderRestaurant> getAllEatInOrderRestaurant();
    EatInOrderRestaurant createEatInOrderRestaurant(EatInOrderRestaurant eatInOrderRestaurant);
    EatInOrderRestaurant getEatInOrderRestaurantById(String id);
    EatInOrderRestaurant updateEatInOrderRestaurant(String id, EatInOrderRestaurant eatInOrderRestaurant);
    boolean deleteEatInOrderRestaurant(String id);

}
