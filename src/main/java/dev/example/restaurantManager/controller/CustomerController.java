package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
//import io.swagger.annotations.*;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {


  @Autowired
    private CustomerService customerService;

    @GetMapping("/allCustomers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable String id, @RequestBody Customer customerDetails) {
        return customerService.updateCustomer(id, customerDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
    }

    // just a drat of getCustomerById with headers and responseEntity
    // just the first version
    //@ApiOperation(value = "Get a customer by Id")
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(
            //@ApiParam(value = "Customer id from which customer object will retrieve by H2 DATABASE on memory", required = true)
            @PathVariable String id) {
        Customer customer = customerService.getCustomerById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", "Get a customer by Id");
        headers.add("content-type", "application/json");
        headers.add("date", "10-01-2022");
        headers.add("server", "H2 Database");
        headers.add("version", "1.0.0");


        return customer != null
                ? ResponseEntity.accepted().headers(headers).body(customer)
                : ResponseEntity.notFound().headers(headers).build();
    }


//-------------------------------------------------------------------------------
//--------------------- old controller with customerRepository -------------------------------------------
//-------------------------------------------------------------------------------

/*
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
*/



}
