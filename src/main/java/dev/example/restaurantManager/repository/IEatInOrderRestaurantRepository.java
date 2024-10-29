package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.EatInOrderRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEatInOrderRestaurantRepository extends JpaRepository<EatInOrderRestaurant, String> {
}
