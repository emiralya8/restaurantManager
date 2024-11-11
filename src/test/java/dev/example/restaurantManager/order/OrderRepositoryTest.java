package dev.example.restaurantManager.order;

import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import dev.example.restaurantManager.repository.OrderMenuQtyRepository;
import dev.example.restaurantManager.repository.OrderRestaurantRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import dev.example.restaurantManager.model.OrderMenuQty;
import dev.example.restaurantManager.model.OrderRestaurant;
import dev.example.restaurantManager.model.MenuRestaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    private OrderMenuQtyRepository orderMenuQtyRepository;
    @Autowired
    private OrderRestaurantRepository orderRestaurantRepository;
    @Autowired
    private MenuRestaurantRepository menuRestaurantRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    public void testDeleteOrderMenuQty() {
        // Get the first OrderMenuQty from the repository
        OrderMenuQty orderMenuQty = orderMenuQtyRepository.findAll(PageRequest.of(0, 1)).getContent().get(0);
        assertThat(orderMenuQty).isNotNull();

        // Store the IDs of associated Order and Menu
        String orderId = orderMenuQty.getOrder().getId();
        String menuId = orderMenuQty.getMenu().getId();

        // Delete the OrderMenuQty
        orderMenuQtyRepository.deleteById(orderMenuQty.getId());

        // Verify the OrderMenuQty has been deleted
        OrderMenuQty deletedOrderMenuQty = orderMenuQtyRepository.findById(orderMenuQty.getId()).orElse(null);
        assertThat(deletedOrderMenuQty).isNull();

        // Verify that the associated Order and Menu still exist
        OrderRestaurant existingOrder = orderRestaurantRepository.findById(orderId).orElse(null);
        MenuRestaurant existingMenu = menuRestaurantRepository.findById(menuId).orElse(null);
        assertThat(existingOrder).isNotNull();
        assertThat(existingMenu).isNotNull();
        assertThat(existingOrder.getId()).isEqualTo(orderId);
        assertThat(existingMenu.getId()).isEqualTo(menuId);
    }

    @Test
    public void testUpdateOrderMenuQty() {
        // Get the first OrderMenuQty from the repository
        OrderMenuQty orderMenuQty = orderMenuQtyRepository.findAll(PageRequest.of(0, 1)).getContent().get(0);
        assertThat(orderMenuQty).isNotNull();

        // Store the original quantity and IDs
        int originalQuantity = orderMenuQty.getQuantity();
        String orderId = orderMenuQty.getOrder().getId();
        String menuId = orderMenuQty.getMenu().getId();

        // Update the quantity
        int newQuantity = originalQuantity + 1;
        orderMenuQty.setQuantity(newQuantity);
        orderMenuQtyRepository.save(orderMenuQty);

        // Retrieve the updated OrderMenuQty
        OrderMenuQty updatedOrderMenuQty = orderMenuQtyRepository.findById(orderMenuQty.getId()).orElse(null);
        assertThat(updatedOrderMenuQty).isNotNull();
        assertThat(updatedOrderMenuQty.getQuantity()).isEqualTo(newQuantity);

        // Verify that the associated Order and Menu still exist and haven't changed
        OrderRestaurant existingOrder = orderRestaurantRepository.findById(orderId).orElse(null);
        MenuRestaurant existingMenu = menuRestaurantRepository.findById(menuId).orElse(null);
        assertThat(existingOrder).isNotNull();
        assertThat(existingMenu).isNotNull();
        assertThat(existingOrder.getId()).isEqualTo(orderId);
        assertThat(existingMenu.getId()).isEqualTo(menuId);

        // Verify that the updated OrderMenuQty still references the same Order and Menu
        assertThat(updatedOrderMenuQty.getOrder().getId()).isEqualTo(orderId);
        assertThat(updatedOrderMenuQty.getMenu().getId()).isEqualTo(menuId);
    }

    @Test
    public void testDeleteOrderMenuQtyFromOrderRestaurant() {
        // Get the first OrderRestaurant from the repository
        OrderRestaurant orderRestaurant = orderRestaurantRepository.findAll(PageRequest.of(0, 1)).getContent().get(0);
        assertThat(orderRestaurant).isNotNull();

        // Store the original number of OrderMenuQty objects
        int originalOrderMenuQtyCount = orderRestaurant.getOrderMenuQties().size();
        assertThat(originalOrderMenuQtyCount).isGreaterThan(0);

        // Store the IDs of the OrderMenuQty objects
        List<String> orderMenuQtyIds = orderRestaurant.getOrderMenuQties().stream()
                .map(OrderMenuQty::getId)
                .toList();

        // Delete all OrderMenuQty objects
        orderMenuQtyRepository.deleteAllById(orderMenuQtyIds);

        // Clear the list in the OrderRestaurant object
        orderRestaurant.getOrderMenuQties().clear();

        // Save the updated OrderRestaurant
        orderRestaurantRepository.save(orderRestaurant);

        // Retrieve the updated OrderRestaurant
        OrderRestaurant updatedOrderRestaurant = orderRestaurantRepository.findById(orderRestaurant.getId()).orElse(null);
        assertThat(updatedOrderRestaurant).isNotNull();

        // Verify that the OrderMenuQty list is empty
        assertThat(updatedOrderRestaurant.getOrderMenuQties()).isEmpty();

        // Verify that the OrderMenuQty objects have been deleted from the database
        for (String id : orderMenuQtyIds) {
            assertThat(orderMenuQtyRepository.findById(id)).isEmpty();
        }
    }

    @Test
    @Transactional
    public void testDeleteOrderMenuQtyFromOrderRestaurant_Solved_1() {
        // Create a MenuRestaurant
        MenuRestaurant menu = new MenuRestaurant();
        menu.setId(UUID.randomUUID().toString());
        menu.setName("Test Menu");
        menu.setContent("Test Description");
        menuRestaurantRepository.save(menu);

        // Create an OrderRestaurant with associated OrderMenuQty objects
        OrderRestaurant orderRestaurant = new OrderRestaurant();
        orderRestaurant.setId(UUID.randomUUID().toString());
        orderRestaurant.setDate(new Date());
        orderRestaurant.setWaiter("Test Waiter");
        orderRestaurant.setPeopleQty(2);
        orderRestaurant.setTotalPayment(50.0);
        orderRestaurant.setPaid(true);
        orderRestaurant.setOrderMenuQties(new ArrayList<>());

        // Add OrderMenuQty objects
        for (int i = 0; i < 3; i++) {
            OrderMenuQty orderMenuQty = new OrderMenuQty();
            orderMenuQty.setId(UUID.randomUUID().toString());
            orderMenuQty.setOrder(orderRestaurant);
            orderMenuQty.setMenu(menu);
            orderMenuQty.setQuantity(i + 1);
            orderRestaurant.getOrderMenuQties().add(orderMenuQty);
        }

        orderRestaurantRepository.save(orderRestaurant);

        // Clear the persistence context
        entityManager.flush();
        entityManager.clear();

        // Retrieve the saved OrderRestaurant
        OrderRestaurant savedOrderRestaurant = orderRestaurantRepository.findById(orderRestaurant.getId()).orElseThrow();
        // Print the saved OrderRestaurant
        System.out.println("savedOrderRestaurant: "+ savedOrderRestaurant);
        System.out.println("Qty OrderMenuQties: " + savedOrderRestaurant.getOrderMenuQties().size());
        savedOrderRestaurant.getOrderMenuQties().forEach(System.out::println);


        // Assert that the OrderRestaurant has OrderMenuQty objects
        assertThat(savedOrderRestaurant.getOrderMenuQties()).isNotEmpty();
        int originalOrderMenuQtyCount = savedOrderRestaurant.getOrderMenuQties().size();
        assertThat(originalOrderMenuQtyCount).isGreaterThan(0);

        // Store the IDs of the OrderMenuQty objects
        List<String> orderMenuQtyIds = savedOrderRestaurant.getOrderMenuQties().stream()
                .map(OrderMenuQty::getId)
                .toList();

        // Delete all OrderMenuQty objects
        orderMenuQtyRepository.deleteAllById(orderMenuQtyIds);

        // Clear the OrderMenuQties list in the OrderRestaurant object
        savedOrderRestaurant.getOrderMenuQties().clear();

        // Save the updated OrderRestaurant
        orderRestaurantRepository.save(savedOrderRestaurant);

        // Clear the persistence context
        entityManager.flush();
        entityManager.clear();

        // Retrieve the updated OrderRestaurant
        OrderRestaurant updatedOrderRestaurant =
                orderRestaurantRepository.findById(savedOrderRestaurant.getId()).orElseThrow();

        // Verify that the OrderMenuQty list is empty
        assertThat(updatedOrderRestaurant.getOrderMenuQties()).isEmpty();

        // Verify that the OrderMenuQty objects have been deleted from the database
        for (String id : orderMenuQtyIds) {
            assertThat(orderMenuQtyRepository.findById(id)).isEmpty();
        }
    }

    @Test
    @Transactional
    public void testDeleteOrderMenuQtyFromOrderRestaurant_Solved_2() {
        // Get the first OrderRestaurant from the repository
        OrderRestaurant orderRestaurant = orderRestaurantRepository.findAll(PageRequest.of(15, 1)).getContent().get(0);
        assertThat(orderRestaurant).isNotNull();

        // Clear the persistence context
        entityManager.clear();

        // Create a list and load it with OrderMenuQty data from the database for this OrderRestaurant
        List<OrderMenuQty> orderMenuQties = orderMenuQtyRepository.findAll().stream()
                .filter(omq -> omq.getOrder().getId().equals(orderRestaurant.getId()))
                .collect(Collectors.toList());

        // Set the OrderMenuQties for the OrderRestaurant
        orderRestaurant.setOrderMenuQties(orderMenuQties);

        // Print the OrderRestaurant and its OrderMenuQties
        System.out.println("OrderRestaurant: " + orderRestaurant);
        System.out.println("Qty OrderMenuQties: " + orderRestaurant.getOrderMenuQties().size());
        orderRestaurant.getOrderMenuQties().forEach(System.out::println);

        // Assert that the OrderRestaurant has OrderMenuQty objects
        assertThat(orderRestaurant.getOrderMenuQties()).isNotEmpty();
        int originalOrderMenuQtyCount = orderRestaurant.getOrderMenuQties().size();
        assertThat(originalOrderMenuQtyCount).isGreaterThan(0);

        // Store the IDs of the OrderMenuQty objects
        List<String> orderMenuQtyIds = orderRestaurant.getOrderMenuQties().stream()
                .map(OrderMenuQty::getId)
                .toList();

        // Delete all OrderMenuQty objects
        orderMenuQtyRepository.deleteAllById(orderMenuQtyIds);

        // Clear the OrderMenuQties list in the OrderRestaurant object
        orderRestaurant.getOrderMenuQties().clear();

        // Save the updated OrderRestaurant
        orderRestaurantRepository.save(orderRestaurant);

        // Clear the persistence context
        entityManager.flush();
        entityManager.clear();

        // Retrieve the updated OrderRestaurant
        OrderRestaurant updatedOrderRestaurant = orderRestaurantRepository.findById(orderRestaurant.getId()).orElseThrow();

        // Verify that the OrderMenuQty list is empty
        assertThat(updatedOrderRestaurant.getOrderMenuQties()).isEmpty();

        // Verify that the OrderMenuQty objects have been deleted from the database
        for (String id : orderMenuQtyIds) {
            assertThat(orderMenuQtyRepository.findById(id)).isEmpty();
        }
    }
    @Test
    @Transactional
    public void testDeleteOrderMenuQtyFromOrderRestaurant_Solved_3() {
        // Get the 15th OrderRestaurant from the repository with its OrderMenuQty objects
        OrderRestaurant orderRestaurant = entityManager.createQuery(
                        "SELECT o FROM OrderRestaurant o LEFT JOIN FETCH o.orderMenuQties WHERE o.id IN :ids",
                        OrderRestaurant.class)
                .setParameter("ids", orderRestaurantRepository.findAll(PageRequest.of(14, 1))
                        .getContent()
                        .stream()
                        .map(OrderRestaurant::getId)
                        .toList())
                .getSingleResult();

        assertThat(orderRestaurant).isNotNull();

        // Print the OrderRestaurant and its OrderMenuQties
        System.out.println("OrderRestaurant: " + orderRestaurant);
        System.out.println("Qty OrderMenuQties: " + orderRestaurant.getOrderMenuQties().size());
        orderRestaurant.getOrderMenuQties().forEach(System.out::println);

        // Assert that the OrderRestaurant has OrderMenuQty objects
        assertThat(orderRestaurant.getOrderMenuQties()).isNotEmpty();
        int originalOrderMenuQtyCount = orderRestaurant.getOrderMenuQties().size();
        assertThat(originalOrderMenuQtyCount).isGreaterThan(0);

        // Store the IDs of the OrderMenuQty objects
        List<String> orderMenuQtyIds = orderRestaurant.getOrderMenuQties().stream()
                .map(OrderMenuQty::getId)
                .toList();

        // Delete all OrderMenuQty objects
        orderMenuQtyRepository.deleteAllById(orderMenuQtyIds);

        // Clear the OrderMenuQties list in the OrderRestaurant object
        orderRestaurant.getOrderMenuQties().clear();

        // Save the updated OrderRestaurant
        orderRestaurantRepository.save(orderRestaurant);

        // Clear the persistence context
        entityManager.flush();
        entityManager.clear();

        // Retrieve the updated OrderRestaurant
        OrderRestaurant updatedOrderRestaurant = entityManager.createQuery(
                        "SELECT o FROM OrderRestaurant o LEFT JOIN FETCH o.orderMenuQties WHERE o.id = :id",
                        OrderRestaurant.class)
                .setParameter("id", orderRestaurant.getId())
                .getSingleResult();

        assertThat(updatedOrderRestaurant).isNotNull();

        // Verify that the OrderMenuQty list is empty
        assertThat(updatedOrderRestaurant.getOrderMenuQties()).isEmpty();

        // Verify that the OrderMenuQty objects have been deleted from the database
        for (String id : orderMenuQtyIds) {
            assertThat(orderMenuQtyRepository.findById(id)).isEmpty();
        }
    }
}



