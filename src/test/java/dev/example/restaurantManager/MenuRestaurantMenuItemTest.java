package dev.example.restaurantManager;

import dev.example.restaurantManager.model.MenuItem;
import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.repository.MenuItemRepository;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import dev.example.restaurantManager.model.enums.CourseType;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class MenuRestaurantMenuItemTest {

    @Autowired
    private MenuRestaurantRepository menuRestaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Test
    public void testRelationshipOfMenuRestaurantMenuItem() {

        //Arrange
        MenuRestaurant menuRestaurant = new MenuRestaurant();
        menuRestaurant.setId(UUID.randomUUID().toString());
        menuRestaurant.setName("Menu Example");
        menuRestaurant.setPrice(18.0);
        menuRestaurant.setContent("Content three dishes");
        menuRestaurant.setActive(true);
        menuRestaurant.setWater(true);

        menuRestaurantRepository.save(menuRestaurant);
        MenuItem menuItemOne = new MenuItem();
        menuItemOne.setName("Item 1");
        menuItemOne.setDescription("Description 1");
        menuItemOne.setPrice(7.5);
        menuItemOne.setCourseType(CourseType.STARTER);
        menuRestaurant.addMenuItem(menuItemOne);
        MenuItem menuItemTwo = new MenuItem();
        menuItemTwo.setName("Item 2");
        menuItemTwo.setDescription("Description 2");
        menuItemTwo.setPrice(10.5);
        menuItemTwo.setCourseType(CourseType.MAIN);
        menuRestaurant.addMenuItem(menuItemTwo);
        MenuItem menuItemThree = new MenuItem();
        menuItemThree.setName("Item 2");
        menuItemThree.setDescription("Description 2");
        menuItemThree.setPrice(10.5);
        menuItemThree.setCourseType(CourseType.DESSERT);
        menuRestaurant.addMenuItem(menuItemThree);
        MenuRestaurant menu = menuRestaurantRepository.save(menuRestaurant);
        //Act
        MenuRestaurant locateMenu = menuRestaurantRepository.findById(menu.getId()).orElse(null);
        MenuItem locateMenuItem = locateMenu.getMenuItems().get(0);

        //Assert
        assertNotNull(locateMenu, "MenuRestaurant not null");
        assertFalse(locateMenu.getMenuItems().isEmpty(), "MenuRestaurant should have at least one MenuItem");
        assertNotNull(locateMenuItem, "MenuItem not null");
        assertEquals(locateMenu.getMenuItems().get(0).getCourseType(),CourseType.STARTER);
        System.out.println(locateMenuItem.toString());
    }
}
