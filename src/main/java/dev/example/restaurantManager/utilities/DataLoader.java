package dev.example.restaurantManager.utilities;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.*;
import dev.example.restaurantManager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class DataLoader {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TableRestaurantRepository tableRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private MenuRestaurantRepository menuRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private OrderRestaurantRepository orderRepository;
    @Autowired
    private OrderMenuQtyRepository orderMenuQtyRepository;

    private final Faker faker = new Faker(new Locale("en-US"));

    // master method orchestrating all other methods to
    // load fake data into the local H2 database
    // and create relationships between entities in the database
    public void loadAllData() {
        createCustomers();
        createTables();
        createMenuItems();

        // create and assign menu items
        createMenusAndAssignMenuItems();
        // create bookings and assign customers and tables
        createBookingsAndAssignTablesAndCustomers();
        // create orders and assign menus and customers
        createOrdersAndAssignMenus();
    }

    // we are going to create 25 customers
    private void createCustomers() {
        for (int i = 0; i < 25; i++) {
            Customer customer = new Customer(
                    UUID.randomUUID().toString(),
                    faker.name().fullName(),
                    faker.internet().emailAddress(),
                    faker.phoneNumber().cellPhone(),
                    faker.random().nextInt(18, 80),
                    faker.random().nextBoolean(),
                    faker.random().nextBoolean()
            );
            customerRepository.save(customer);
        }
    }

    // we are going to create 10 tables
    private void createTables() {
        for (int i = 0; i < 10; i++) {
            TableRestaurant table = new TableRestaurant(
                    UUID.randomUUID().toString(),
                    faker.name().fullName(),
                    faker.lorem().sentence(),
                    faker.random().nextInt(2, 8),
                    faker.bool().bool()
            );
            tableRepository.save(table);
        }
    }

    // we are going to create 25 menu items
    private void createMenuItems() {
        for (int i = 0; i < 25; i++) {
            MenuItem menuItem = new MenuItem(
                    UUID.randomUUID().toString(),
                    faker.food().dish(),
                    faker.food().ingredient() + " " + faker.food().ingredient() ,
                    faker.number().randomDouble(2, 5, 30)
            );
            menuItemRepository.save(menuItem);
        }
    }

    // we are going to create 15 menus
    // and assign 5 to 10 menu items to each menu
    // to create a many-to-many relationship
    private void createMenusAndAssignMenuItems() {
        List<MenuItem> allItems = menuItemRepository.findAll();
        for (int i = 0; i < 15; i++) {
            MenuRestaurant menu = new MenuRestaurant(
                    UUID.randomUUID().toString(),
                     " Menu-00" + i,
                    faker.lorem().paragraph()
            );
            List<MenuItem> menuItems = new ArrayList<>(faker.random().nextInt(5, 10));
            for (int j = 0; j < faker.random().nextInt(5, 10); j++) {
                menuItems.add(allItems.get(faker.random().nextInt(allItems.size())));
            }
            menu.setMenuItems(menuItems);
            menuRepository.save(menu);
        }
    }

    // we are going to create 25 bookings
    // and assign a customer and table
    // to create a one-to-many relationship and then
    // save the booking in the database
    private void createBookingsAndAssignTablesAndCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<TableRestaurant> tables = tableRepository.findAll();
        for (int i = 0; i < 25; i++) {
            Booking booking = new Booking(
                    UUID.randomUUID().toString(),
                    faker.random().nextInt(1, 8),
                    new Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()),
                    new Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()),
                    faker.options().option("Lunch", "Dinner")
            );
            booking.setCustomerMapped(customers.get(faker.random().nextInt(customers.size())));
            booking.setTableRestaurantMapped(tables.get(faker.random().nextInt(tables.size())));
            bookingRepository.save(booking);
        }
    }

    // we are going to create 45 orders and then
    // create order menu quantities for each order
    // to create a many-to-many relationship
    private void createOrdersAndAssignMenus() {
        List<MenuRestaurant> menus = menuRepository.findAll();
        for (int i = 0; i < 45; i++) {
            OrderRestaurant order = new OrderRestaurant(
                    UUID.randomUUID().toString(),
                    new Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()),
                    faker.name().fullName(),
                    faker.random().nextInt(1, 8),
                    faker.number().randomDouble(2, 5, 50),
                    faker.bool().bool()
            );

            List<OrderMenuQty> orderMenuQties = new ArrayList<>();
            for (int j = 0; j < faker.random().nextInt(1, 5); j++) {
                OrderMenuQty orderMenuQty = new OrderMenuQty();
                orderMenuQty.setId(UUID.randomUUID().toString());
                orderMenuQty.setOrder(order);
                orderMenuQty.setMenu(menus.get(faker.random().nextInt(menus.size())));
                orderMenuQty.setQuantity(faker.random().nextInt(1, 5));
                orderMenuQties.add(orderMenuQty);
            }
            order.setOrderMenuQties(orderMenuQties);
            orderRepository.save(order);
        }
    }
}