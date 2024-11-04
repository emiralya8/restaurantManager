package dev.example.restaurantManager.Tests;

import dev.example.restaurantManager.model.Booking;
import dev.example.restaurantManager.model.EatInOrderRestaurant;
import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.TableRestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TableRestaurantRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TableRestaurantRepository tableRestaurantRepository;

    @Test
    public void whenFindById_thenReturnTableRestaurant() {
        // given
        Booking bk1 = new Booking();
        bk1.setId("book1");
        bk1.setName("Juan");
        bk1.setDate(new Date("25/12/2024"));
        bk1.setConfirmed(true);
        bk1.setPeopleQty(10);
        //entityManager.persist(bk1);
        //entityManager.flush();
        //ArrayList<Booking> lstBookings = new ArrayList<>();
        //lstBookings.add(bk1);

        TableRestaurant tabla1 = new TableRestaurant();
        tabla1.setId("tabla1");
        tabla1.setQty(4);
        tabla1.setName("Juan");
        tabla1.setDescription("Tabla Sala 1");
        tabla1.setBusy(true);
        tabla1.getBookings().add(bk1);

        EatInOrderRestaurant eatInOrder01 = new EatInOrderRestaurant("Eat01",new Date("15/12/2024"),"waiter01",10,0.0,false,new ArrayList<>(), tabla1);
        //ArrayList<EatInOrderRestaurant> lstEatInOrders = new ArrayList<>();
        //entityManager.persist(eatInOrder01);
        //entityManager.flush();

        //lstEatInOrders.add(eatInOrder01);

        tabla1.getEatInOrderRestaurant().add(eatInOrder01);

        entityManager.persist(tabla1);
        entityManager.flush();

        // when
        Optional<TableRestaurant> found = tableRestaurantRepository.findById(tabla1.getId());

        // then
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo(tabla1.getName());
    }

}