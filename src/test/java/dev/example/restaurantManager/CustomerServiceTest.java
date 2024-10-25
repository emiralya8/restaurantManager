package dev.example.restaurantManager;



import dev.example.restaurantManager.model.Booking;
import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.repository.CustomerRepository;
import dev.example.restaurantManager.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
//@AutoConfigureMockMvc
public class CustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    public void customerServiceTest() {

        Customer alex = new Customer("1", "Alex", "alex@example.com",
                "1234567890", 30, false, false);
        Customer customerCreated = customerService.createCustomer(alex);

        String id = customerCreated.getId();

        System.out.println("Customer created: " + customerCreated);
        System.out.println("Customer found: " + customerService.getCustomerById(id));
        System.out.println("Customer repository: " + customerRepository.findById(id));

        assertThat(customerCreated).isNotNull();
    }
}