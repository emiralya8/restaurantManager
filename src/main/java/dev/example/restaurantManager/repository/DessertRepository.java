package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.Dessert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DessertRepository extends JpaRepository<Dessert,String> {
}