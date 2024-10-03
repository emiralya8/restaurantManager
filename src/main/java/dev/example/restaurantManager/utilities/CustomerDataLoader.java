package dev.example.restaurantManager.utilities;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.UUID;

@Component
public class CustomerDataLoader {

    @Autowired
    private CustomerRepository customerRepository;

    public void createFakeCustomers() {
        // Check if the database is empty
        if (customerRepository.count() == 0) {
            Faker faker = new Faker(new Locale("en-US"));

            // Create and save 100 fake customers
            for (int i = 0; i < 100; i++) {
                Customer customer = new Customer(
                        UUID.randomUUID().toString(),
                        faker.name().fullName(),
                        faker.internet().emailAddress(),
                        faker.phoneNumber().cellPhone()
                );
                customerRepository.save(customer);
            }

            System.out.println("100 fake customers have been created and saved to the database.");
        }
    }
}