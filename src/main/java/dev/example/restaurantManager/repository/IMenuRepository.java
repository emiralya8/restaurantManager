package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.model.Menu;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IMenuRepository extends JpaRepository<Menu, String> {

    // 1. Find menus by name
    Optional<Menu> findByName(String name);


    // 2. Find menus by content using Like
    @Query(value ="SELECT * FROM menu WHERE content LIKE %?1%", nativeQuery = true)
    List<Menu> findByContent(String contentDescription);

}
