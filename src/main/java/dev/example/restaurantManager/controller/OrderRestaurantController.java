package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.OrderRestaurant;
import dev.example.restaurantManager.service.OrderRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderRestaurantController {

    @Autowired
    private OrderRestaurantService orderRestaurantService;

    @GetMapping
    public ResponseEntity<List<OrderRestaurant>> getAllOrders() {
        List<OrderRestaurant> orders = orderRestaurantService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderRestaurant> createOrder(@RequestBody OrderRestaurant order) {
        OrderRestaurant newOrder = orderRestaurantService.createOrder(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderRestaurant> getOrderById(@PathVariable String id) {
        OrderRestaurant order = orderRestaurantService.getOrderById(id);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderRestaurant> updateOrder(@PathVariable String id, @RequestBody OrderRestaurant orderDetails) {
        OrderRestaurant updatedOrder = orderRestaurantService.updateOrder(id, orderDetails);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        boolean deleted = orderRestaurantService.deleteOrder(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countOrders() {
        long count = orderRestaurantService.countOrders();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @PostMapping("/{orderId}/menus/{menuId}")
    public ResponseEntity<OrderRestaurant> addMenuToOrder(@PathVariable String orderId,
                                                          @PathVariable String menuId,
                                                          @RequestParam int quantity) {
        OrderRestaurant updatedOrder = orderRestaurantService.addMenuToOrder(orderId, menuId, quantity);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{orderId}/menus/{menuId}")
    public ResponseEntity<OrderRestaurant> removeMenuFromOrder(@PathVariable String orderId,
                                                               @PathVariable String menuId) {
        OrderRestaurant updatedOrder = orderRestaurantService.removeMenuFromOrder(orderId, menuId);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}