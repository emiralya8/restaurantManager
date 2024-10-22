package dev.example.restaurantManager.utilities.fakers;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.TableRestaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class TableRestaurantFaker implements IFaker<TableRestaurant>{

    private final Faker faker = new Faker(new Locale("en-US"));

    @Override
    public TableRestaurant CreateObject(TableRestaurant object) {
        return new TableRestaurant(
                UUID.randomUUID().toString(),
                faker.funnyName().name(),
                faker.number().numberBetween(1,7),
                faker.random().nextBoolean()
        );
    }

    @Override
    public List<TableRestaurant> GetNObjects(int n) {
        List<TableRestaurant> lstTableRestaurant = new ArrayList<TableRestaurant>();
        for (int i = 0; i < n; i++) {
            TableRestaurant tableRestaurant = new TableRestaurant(
                    UUID.randomUUID().toString(),
                    faker.funnyName().name(),
                    faker.number().numberBetween(1,7),
                    faker.random().nextBoolean()
            );
            lstTableRestaurant.add(tableRestaurant);
        }
        return lstTableRestaurant;
    }
}
