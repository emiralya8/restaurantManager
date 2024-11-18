package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.OrderRestaurant;
import dev.example.restaurantManager.repository.OrderMenuQtyRepository;
import dev.example.restaurantManager.repository.OrderRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class OrderRestaurantServiceImpl implements OrderRestaurantService{

    @Autowired
    private OrderRestaurantRepository orderRestaurantRepository;

    @Override
    public List<OrderRestaurant> getAllOrders() {
        return orderRestaurantRepository.findAll();
    }

    @Override
    public OrderRestaurant getOrderById(String id) {
        return orderRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public OrderRestaurant createOrder(OrderRestaurant order) {
        return orderRestaurantRepository.save(order);
    }

    @Override
    public OrderRestaurant updateOrder(String id, OrderRestaurant orderDetails) {
        boolean isTrue = false;
        OrderRestaurant OrderRestaurantElement = orderRestaurantRepository.findById(id).orElse(null);
        if(OrderRestaurantElement != null){
            Field[] fields = orderDetails.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    if(field.get(OrderRestaurantElement) != field.get(orderDetails)){
                        isTrue = true;
                        field.set(OrderRestaurantElement,field.get(orderDetails));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            orderRestaurantRepository.save(OrderRestaurantElement);
        }

        return isTrue ? OrderRestaurantElement : orderDetails;
    }

    @Override
    public boolean deleteOrder(String id) {
        Optional<OrderRestaurant> order = orderRestaurantRepository.findById(id);
        if(order.isEmpty()){
            return false;
        }else{
            orderRestaurantRepository.deleteById(id);
            return true;
        }
    }

    @Override
    public long countOrders() {
        return orderRestaurantRepository.count();
    }
}
