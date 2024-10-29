package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.OrderRestaurant;
import dev.example.restaurantManager.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderService implements IService<OrderRestaurant>{

    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public List<OrderRestaurant> getAllElements() {
        return orderRepository.findAll();
    }

    @Override
    public OrderRestaurant createElement(OrderRestaurant element) {
        return orderRepository.save(element);
    }

    @Override
    public OrderRestaurant getElementById(String id) {
        return null;
    }

    @Override
    public OrderRestaurant updateElement(String id, OrderRestaurant eDetails) {
        return null;
    }

    @Override
    public boolean deleteElement(String id) {
        return false;
    }

    @Override
    public long countElements() {
        return 0;
    }
}
