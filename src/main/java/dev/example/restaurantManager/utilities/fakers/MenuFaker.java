package dev.example.restaurantManager.utilities.fakers;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.model.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class MenuFaker implements IFaker<Menu>{

    private final Faker faker = new Faker(new Locale("en-US"));

    @Override
    public Menu CreateObject(Menu object) {
        return new Menu(
                UUID.randomUUID().toString(),
                faker.name().fullName(),
                faker.number().randomDouble(2, 18,130),
                faker.food().dish(),
                true,
                faker.random().nextBoolean()
        );
    }

    @Override
    public List<Menu> GetNObjects(int n) {
        List<Menu> lstMenu = new ArrayList<Menu>();
        for (int i = 0; i < n; i++) {
            Menu menu = new Menu(
                    UUID.randomUUID().toString(),
                    faker.name().fullName(),
                    faker.number().randomDouble(2, 18,130),
                    faker.food().dish(),
                    true,
                    faker.random().nextBoolean()
            );
            lstMenu.add(menu);
        }
        return lstMenu;
    }
}
