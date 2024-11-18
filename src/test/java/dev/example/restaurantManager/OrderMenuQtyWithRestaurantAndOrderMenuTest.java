package dev.example.restaurantManager;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.model.OrderMenuQty;
import dev.example.restaurantManager.model.OrderRestaurant;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import dev.example.restaurantManager.repository.OrderMenuQtyRepository;
import dev.example.restaurantManager.repository.OrderRestaurantRepository;
import dev.example.restaurantManager.service.OrderRestaurantService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

@DataJpaTest
public class OrderMenuQtyWithRestaurantAndOrderMenuTest {

    @Autowired
    private MenuRestaurantRepository menuRestaurantRepository;
    @Autowired
    private OrderMenuQtyRepository orderMenuQtyRepository;
    @Autowired
    private OrderRestaurantRepository orderRestaurantRepository;

    @Test
    public void orderServiceAddMenusAndAddOrderMenuQty(){
        // store in memory menus
        MenuRestaurant menuRestaurant1 = new MenuRestaurant("M01", "Burger Menu", 10.5, "Burger, fries, and drink", true, true);
        MenuRestaurant menuRestaurant2 = new MenuRestaurant("M02", "Pizza Menu", 17.5, "Pizza and salad", true, false);
        MenuRestaurant menuRestaurant3 = new MenuRestaurant("M03", "Salad Menu", 8.0, "Mixed salad and dressing", true, true);
        menuRestaurantRepository.save(menuRestaurant1);
        menuRestaurantRepository.save(menuRestaurant2);
        menuRestaurantRepository.save(menuRestaurant3);

        OrderRestaurant order1 = new OrderRestaurant("O01", new Date(), "JohnQ", 4, 49.5, true, null);
        OrderRestaurant order2 = new OrderRestaurant("002", new Date(), "Ernesto", 5, 35.10, true, null);
        OrderRestaurant order3 = new OrderRestaurant("003", new Date(), "Juan", 6, 60.5, true, null);
        orderRestaurantRepository.save(order1);
        orderRestaurantRepository.save(order2);
        orderRestaurantRepository.save(order3);

        OrderMenuQty orderMenuQty1 = new OrderMenuQty("1",5,null,null);
        OrderMenuQty orderMenuQty2 = new OrderMenuQty("2",4,null,null);
        OrderMenuQty orderMenuQty3 = new OrderMenuQty("3",3,null,null);

        orderMenuQty1.setMenuRestaurant(menuRestaurant1);
        orderMenuQty2.setMenuRestaurant(menuRestaurant2);
        orderMenuQty3.setMenuRestaurant(menuRestaurant3);

        orderMenuQty1.setOrderRestaurant(order1);
        orderMenuQty2.setOrderRestaurant(order2);
        orderMenuQty3.setOrderRestaurant(order3);

        orderMenuQtyRepository.save(orderMenuQty1);
        orderMenuQtyRepository.save(orderMenuQty2);
        orderMenuQtyRepository.save(orderMenuQty3);

        System.out.println(orderMenuQty1.toString());
        order1.addOrderMenuQty(orderMenuQty1);
        order1.addOrderMenuQty(orderMenuQty2);
        order1.addOrderMenuQty(orderMenuQty3);
        System.out.println(order1.toString());
        order1.getOrderMenuQtyIds().forEach(System.out::println);

    }
}
