package dev.example.restaurantManager.utilities.fakers;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.Menu;
import dev.example.restaurantManager.model.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class TableFaker implements IFaker<Table>{

    private final Faker faker = new Faker(new Locale("en-US"));

    @Override
    public Table CreateObject(Table object) {
        return new Table(
                UUID.randomUUID().toString(),
                faker.funnyName().name(),
                faker.funnyName().name().toString(),
                faker.number().numberBetween(1,7),
                faker.random().nextBoolean()
        );
    }

    @Override
    public List<Table> GetNObjects(int n) {
        List<Table> lstTable = new ArrayList<Table>();
        for (int i = 0; i < n; i++) {
            Table table = new Table(
                    UUID.randomUUID().toString(),
                    faker.funnyName().name(),
                    faker.funnyName().name().toString(),
                    faker.number().numberBetween(1,7),
                    faker.random().nextBoolean()
            );
            lstTable.add(table);
        }
        return lstTable;
    }
}
