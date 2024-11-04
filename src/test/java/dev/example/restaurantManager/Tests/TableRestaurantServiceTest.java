package dev.example.restaurantManager.Tests;

import dev.example.restaurantManager.model.Booking;
import dev.example.restaurantManager.model.EatInOrderRestaurant;
import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.TableRestaurantRepository;
import dev.example.restaurantManager.service.TableRestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TableRestaurantServiceTest {

    @Autowired
    private TableRestaurantService tableRestaurantService;

    @MockBean
    private TableRestaurantRepository tableRestaurantRepository;

    //    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void check_add_new_table_restaurant(){
        // Define the behavior of the mocked dependency
        Booking bk1 = new Booking();
        bk1.setId("book1");
        bk1.setName("Juan");
        bk1.setDate(new Date("25/12/2024"));
        bk1.setConfirmed(true);
        bk1.setPeopleQty(10);

        TableRestaurant tabla1 = new TableRestaurant();
        tabla1.setId("tabla1");
        tabla1.setQty(4);
        tabla1.setName("Juan");
        tabla1.setDescription("Tabla Sala 1");
        tabla1.setBusy(true);
        tabla1.getBookings().add(bk1);

        EatInOrderRestaurant eatInOrder01 = new EatInOrderRestaurant("Eat01",new Date("15/12/2024"),"waiter01",10,0.0,false,new ArrayList<>(), tabla1);
        tabla1.getEatInOrderRestaurant().add(eatInOrder01);

        when(tableRestaurantRepository.save(any(TableRestaurant.class))).thenReturn(tabla1);
        when(tableRestaurantRepository.findById(any(String.class))).thenReturn(Optional.of(tabla1));
        //        Arrange

        TableRestaurant tb1 = tableRestaurantService.createTableRestaurant(tabla1);

        //        Act
        TableRestaurant findTableAfterToSave = tableRestaurantService.getTableRestaurantById(tabla1.getId());
        TableRestaurant findTableRestaurant = tableRestaurantRepository.findById(tabla1.getId()).orElse(null);

        //        Assert
        assertThat(findTableAfterToSave.getName()).isEqualTo(findTableRestaurant.getName());
    }
}
