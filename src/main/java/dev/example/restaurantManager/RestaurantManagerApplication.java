package dev.example.restaurantManager;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Locale;
import java.util.UUID;

@SpringBootApplication
public class RestaurantManagerApplication {

	public static void main(String[] args) {

		SpringApplication.run(RestaurantManagerApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(CustomerRepository customerRepository) {
		return args -> {
			// Check if the database is empty
			if (customerRepository.count() == 0) {
				Faker faker = new Faker(new Locale("en-US"));

				// Create and save 100 fake customers
				for (int i = 0; i < 1000; i++) {
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
		};
	}

}
