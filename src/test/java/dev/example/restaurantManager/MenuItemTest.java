package dev.example.restaurantManager;


import dev.example.restaurantManager.model.Dessert;
import dev.example.restaurantManager.model.MainCourse;
import dev.example.restaurantManager.repository.DessertRepository;
import dev.example.restaurantManager.repository.MainCourseRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class MenuItemTest {

    @Autowired
    private DessertRepository dessertRepository;
    @Autowired
    private MainCourseRepository mainCourseRepository;

    @Test
    public void dessertTest() {
        Dessert dessert = new Dessert(
                "MD001",
                "Carrot Cake",
                "Carrot sweet",
                7.0,
                true
        );
        dessertRepository.save(dessert);

        Dessert found = dessertRepository.findById("MD001").orElse(null);
        assertNotNull(found);
        assertEquals(dessert.getDescription(), found.getDescription());
        assertTrue(found.isSugarFree());
    }

    @Test
    public void mainCourseTest() {

        MainCourse mainCourse = new MainCourse(
                "MC001",
                "Toat",
                "Potatoes with pig",
                17.50,
                false
        );
        mainCourseRepository.save(mainCourse);

        MainCourse found = mainCourseRepository.findById("MC001").orElse(null);
        assertNotNull(found);
        assertEquals(mainCourse.getName(), found.getName());
        assertEquals(mainCourse.getDescription(), found.getDescription());
    }
}
