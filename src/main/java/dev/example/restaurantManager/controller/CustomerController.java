package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.repository.CustomerRepository;
import dev.example.restaurantManager.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    // CRUD: read for customer
    @GetMapping("/allCustomers")
    public List<Customer> getAllCustomers() {
        //return customerRepository.findAll();
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    // CRUD: create for customer
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        // Generate a unique ID
        customer.setId(UUID.randomUUID().toString());
        return customerRepository.save(customer);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable String id) {
        return customerRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable String id) {
        customerRepository.deleteById(id);
    }

    // Update a customer by ID and return the updated customer
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable String id, @RequestBody Customer customerDetails) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customer.setName(customerDetails.getName());
            customer.setEmail(customerDetails.getEmail());
            return customerRepository.save(customer);
        }
        return null;
    }





    //---------------------------------------------------------------
    // with service layer and controller layer integration
    //---------------------------------------------------------------



/*    @Autowired
    private CustomerService customerService;

    @GetMapping("/allCustomers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable String id, @RequestBody Customer customerDetails) {
        return customerService.updateCustomer(id, customerDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
    }*/

}
