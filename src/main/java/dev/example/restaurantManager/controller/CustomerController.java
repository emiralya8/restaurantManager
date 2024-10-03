package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.repository.CustomerRepository;
import dev.example.restaurantManager.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    // CRUD for customer
    @GetMapping("/allCustomers")
    public List<Customer> getAllCustomers() {

        List<Customer> customers = customerRepository.findAll();

        return customers;
    }


}
