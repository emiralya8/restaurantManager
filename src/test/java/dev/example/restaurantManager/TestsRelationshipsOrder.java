package dev.example.restaurantManager;

import dev.example.restaurantManager.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@SpringBootTest
public class TestsRelationshipsOrder {

    @Test
    public void TestCreateOrder() {
            // Create sample menus
            Menu menu1 = new Menu("Burger Menu", 10.99, "Burger, fries, and drink", true, true);
            Menu menu2 = new Menu("Pizza Menu", 12.99, "Pizza and salad", true, false);
            Menu menu3 = new Menu("Salad Menu", 8.99, "Mixed salad and dressing", true, true);

            // Create sample tables
            TableRestaurant table1 = new TableRestaurant("T1", "Window Table", 4, false);
            TableRestaurant table2 = new TableRestaurant("T2", "Corner Table", 2, true);

            // Create 3 ShippingOrder objects
            Order shippingOrder1 = new ShippingOrder("SO1", new Date(), "John", 2, 21.98, false, new ArrayList<>(Arrays.asList(menu1, menu1)), "123 Main St", "New York", "Mike");
            Order shippingOrder2 = new ShippingOrder("SO2", new Date(), "Sarah", 1, 12.99, true, new ArrayList<>(Arrays.asList(menu2)), "456 Elm St", "Los Angeles", "Tom");
            Order shippingOrder3 = new ShippingOrder("SO3", new Date(), "Emily", 3, 32.97, false, new ArrayList<>(Arrays.asList(menu1, menu2, menu3)), "789 Oak St", "Chicago", "Lisa");

            // Create 3 EatInOrder objects
            Order eatInOrder1 = new EatInOrder("EO1", new Date(), "David", 4, 43.96, true, new ArrayList<>(Arrays.asList(menu1, menu1, menu2, menu2)), new ArrayList<>(Arrays.asList(table1)));
            Order eatInOrder2 = new EatInOrder("EO2", new Date(), "Anna", 2, 21.98, false, new ArrayList<>(Arrays.asList(menu2, menu3)), new ArrayList<>(Arrays.asList(table2)));
            Order eatInOrder3 = new EatInOrder("EO3", new Date(), "Mark", 6, 65.94, true, new ArrayList<>(Arrays.asList(menu1, menu1, menu2, menu2, menu3, menu3)), new ArrayList<>(Arrays.asList(table1, table2)));

            // Create 3 TakeAwayOrder objects
            Order takeAwayOrder1 = new TakeAwayOrder("TO1", new Date(), "Paul", 1, 10.99, true, new ArrayList<>(Arrays.asList(menu1)), "Alice");
            Order takeAwayOrder2 = new TakeAwayOrder("TO2", new Date(), "Linda", 2, 21.98, false, new ArrayList<>(Arrays.asList(menu2, menu3)), "Bob");
            Order takeAwayOrder3 = new TakeAwayOrder("TO3", new Date(), "George", 3, 32.97, true, new ArrayList<>(Arrays.asList(menu1, menu2, menu3)), "Charlie");

            // Create a list of all orders
            ArrayList<Order> orders = new ArrayList<Order>();
            orders.addAll(Arrays.asList(shippingOrder1, shippingOrder2, shippingOrder3,
                    eatInOrder1, eatInOrder2, eatInOrder3,
                    takeAwayOrder1, takeAwayOrder2, takeAwayOrder3));

            // Print the number of orders
            System.out.println("Orders");
            System.out.println("Total number of orders: " + orders.size() + " orders.");
            System.out.println("--------------------");
            // Print all orders
            for (Order order : orders) {
                System.out.println("Order ID: " + order.getId());
                System.out.println(order);
                System.out.println("--------------------");
            }
    }

}
