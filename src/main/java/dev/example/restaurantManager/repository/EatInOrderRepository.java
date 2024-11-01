package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.model.EatInOrderRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EatInOrderRepository extends JpaRepository<EatInOrderRestaurant,String> {
    EatInOrderRestaurant findByOrderedTableRestaurant(String orderedTableRestaurant);
}