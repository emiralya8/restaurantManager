package dev.example.restaurantManager.utilities.fakers;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.MenuRestaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class MenuFaker implements IFaker<MenuRestaurant>{

    private final Faker faker = new Faker(new Locale("en-US"));

    @Override
    public MenuRestaurant CreateObject(MenuRestaurant object) {
        return new MenuRestaurant(
                UUID.randomUUID().toString(),
                faker.food().dish(),
                faker.number().randomDouble(2, 18,130),
                faker.food().ingredient(),
                true,
                faker.random().nextBoolean()
        );
    }

    @Override
    public List<MenuRestaurant> GetNObjects(int n) {
        List<MenuRestaurant> lstMenu = new ArrayList<MenuRestaurant>();
        for (int i = 0; i < n; i++) {
            MenuRestaurant menu = new MenuRestaurant(
                    UUID.randomUUID().toString(),
                    faker.food().dish(),
                    faker.number().randomDouble(2, 18,130),
                    faker.food().ingredient(),
                    true,
                    faker.random().nextBoolean()
            );
            lstMenu.add(menu);
        }
        return lstMenu;
    }
}
