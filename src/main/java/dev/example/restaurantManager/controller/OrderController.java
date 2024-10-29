package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.model.OrderRestaurant;
import dev.example.restaurantManager.repository.ICustomerRepository;
import dev.example.restaurantManager.repository.IEatInOrderRestaurantRepository;
import dev.example.restaurantManager.service.IService;
import org.aspectj.weaver.ast.Or;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

public class OrderController {

    @Autowired
    private IService<OrderRestaurant> orderService;

    // manage request by ResponseEntity with all customers
    @GetMapping("/allCustomers")
    public ResponseEntity<List<OrderRestaurant>> getAllCustomers( ) {
        List<OrderRestaurant> orders = orderService.getAllElements();
        HttpHeaders headers = getCommonHeaders("Get all customers");

        return orders != null && !orders.isEmpty()
                ? new ResponseEntity<>(orders, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<OrderRestaurant> createOrders(@RequestBody OrderRestaurant order) {

        OrderRestaurant createdOrder = orderService.createElement(order);
        HttpHeaders headers = getCommonHeaders("Create a new customer");

        return createdOrder != null
                ? new ResponseEntity<>(createdOrder, headers, HttpStatus.CREATED)
                : new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }

    private HttpHeaders getCommonHeaders(String description) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", description);
        headers.add("content-type", "application/json");
        headers.add("date", new Date().toString());
        headers.add("server", "H2 Database");
        headers.add("version", "1.0.0");
        headers.add("object", "orders");
        return headers;
    }
}
