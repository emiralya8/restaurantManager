package dev.example.restaurantManager.order;

import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import dev.example.restaurantManager.repository.OrderMenuQtyRepository;
import dev.example.restaurantManager.repository.OrderRestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import dev.example.restaurantManager.model.OrderMenuQty;
import dev.example.restaurantManager.model.OrderRestaurant;
import dev.example.restaurantManager.model.MenuRestaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    private OrderMenuQtyRepository orderMenuQtyRepository;
    @Autowired
    private OrderRestaurantRepository orderRestaurantRepository;
    @Autowired
    private MenuRestaurantRepository menuRestaurantRepository;

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
}



