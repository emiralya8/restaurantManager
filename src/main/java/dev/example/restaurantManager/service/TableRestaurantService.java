package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.model.TableRestaurant;

import java.util.List;

public interface TableRestaurantService {
    List<TableRestaurant> getAllTableRestaurant();
    TableRestaurant createTableRestaurant(TableRestaurant tableRestaurant);
    TableRestaurant getTableRestaurantById(String id);
    TableRestaurant updateTableRestaurant(String id, TableRestaurant tableRestaurant);
    boolean deleteTableRestaurant(String id);

}
