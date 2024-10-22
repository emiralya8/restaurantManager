package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.MenuRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IMenuRepository extends JpaRepository<MenuRestaurant, String> {

    // 1. Find menus by name
    Optional<MenuRestaurant> findByName(String name);


    // 2. Find menus by content using Like
    @Query(value ="SELECT * FROM menu WHERE content LIKE %?1%", nativeQuery = true)
    List<MenuRestaurant> findByContent(String contentDescription);

}
