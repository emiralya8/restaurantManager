package dev.example.restaurantManager.Tests;

import dev.example.restaurantManager.controller.TableRestaurantController;
import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.service.TableRestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(TableRestaurantController.class)
public class TableRestaurantControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TableRestaurantService tableRestaurantService;

    @Test
    public void testControllerMethod_getAllTableRestaurant() throws Exception{
        //Arrange

        //Act

        //Assert
        mockMvc.perform(get("/api/v1/tableRestaurant/allTableRestaurants")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }
}
