package dev.example.restaurantManager.utilities.fakers;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class CustomerFaker implements IFaker<Customer> {

    private final Faker faker = new Faker(new Locale("en-US"));

    @Override
    public Customer CreateObject(Customer object) {
        return new Customer(
                UUID.randomUUID().toString(),
                faker.name().fullName(),
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(),
                faker.random().nextInt(18, 130),
                faker.random().nextBoolean(),
                faker.random().nextBoolean()

        );
    }

    @Override
    public List<Customer> GetNObjects(int n) {
        List<Customer> lstCustomer = new ArrayList<Customer>();
        for (int i = 0; i < n; i++) {
            Customer customer = new Customer(
                    UUID.randomUUID().toString(),
                    faker.name().fullName(),
                    faker.internet().emailAddress(),
                    faker.phoneNumber().cellPhone(),
                    faker.random().nextInt(18, 130),
                    faker.random().nextBoolean(),
                    faker.random().nextBoolean()
            );
            lstCustomer.add(customer);
        }
        return lstCustomer;
    }
}