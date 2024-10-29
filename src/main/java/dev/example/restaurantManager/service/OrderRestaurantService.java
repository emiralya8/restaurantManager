package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.OrderRestaurant;
import java.util.List;

public interface OrderRestaurantService {
    List<OrderRestaurant> getAllOrders();
    OrderRestaurant createOrder(OrderRestaurant order);
    OrderRestaurant getOrderById(String id);
    OrderRestaurant updateOrder(String id, OrderRestaurant orderDetails);
    boolean deleteOrder(String id);
    long countOrders();
    OrderRestaurant addMenuToOrder(String orderId, String menuId, int quantity);
    OrderRestaurant removeMenuFromOrder(String orderId, String menuId);
}