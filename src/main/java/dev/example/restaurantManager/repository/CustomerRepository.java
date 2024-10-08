package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    int countByName(String name);
    ArrayList<Customer> findByName(String name);
    long count();

}