package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.OrderRestaurant;
import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.model.OrderMenuQty;
import dev.example.restaurantManager.repository.OrderRestaurantRepository;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class OrderRestaurantServiceImpl implements OrderRestaurantService {

    @Autowired
    private OrderRestaurantRepository orderRestaurantRepository;

    @Autowired
    private MenuRestaurantRepository menuRestaurantRepository;

    @Override
    public List<OrderRestaurant> getAllOrders() {
        return orderRestaurantRepository.findAll();
    }

    @Override
    public OrderRestaurant createOrder(OrderRestaurant order) {
        order.setId(UUID.randomUUID().toString());
        return orderRestaurantRepository.save(order);
    }

    @Override
    public OrderRestaurant getOrderById(String id) {
        return orderRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public OrderRestaurant updateOrder(String id, OrderRestaurant orderDetails) {
        OrderRestaurant order = orderRestaurantRepository.findById(id).orElse(null);
        if (order != null) {
            order.setDate(orderDetails.getDate());
            order.setWaiter(orderDetails.getWaiter());
            order.setPeopleQty(orderDetails.getPeopleQty());
            order.setTotalPayment(orderDetails.getTotalPayment());
            order.setPaid(orderDetails.isPaid());
            return orderRestaurantRepository.save(order);
        }
        return null;
    }

    @Override
    public boolean deleteOrder(String id) {
        if (orderRestaurantRepository.existsById(id)) {
            orderRestaurantRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public long countOrders() {
        return orderRestaurantRepository.count();
    }

    @Override
    public OrderRestaurant addMenuToOrder(String orderId, String menuId, int quantity) {
        OrderRestaurant order = orderRestaurantRepository.findById(orderId).orElse(null);
        MenuRestaurant menu = menuRestaurantRepository.findById(menuId).orElse(null);

        if (order != null && menu != null) {
            OrderMenuQty orderMenuQty = new OrderMenuQty();
            orderMenuQty.setOrder(order);
            orderMenuQty.setMenu(menu);
            orderMenuQty.setQuantity(quantity);
            order.getOrderMenuQties().add(orderMenuQty);
            return orderRestaurantRepository.save(order);
        }
        return null;
    }

    @Override
    public OrderRestaurant removeMenuFromOrder(String orderId, String menuId) {
        OrderRestaurant order = orderRestaurantRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.getOrderMenuQties().removeIf(omq -> omq.getMenu().getId().equals(menuId));
            return orderRestaurantRepository.save(order);
        }
        return null;
    }
}