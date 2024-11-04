package dev.example.restaurantManager.utilities;

import dev.example.restaurantManager.repository.CustomerRepository;
import dev.example.restaurantManager.utilities.fakers.CustomerFaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResourceDataLoader {

    @Autowired
    private CustomerRepository customerRepository;

    public void createFakeCustomers() {
        // Check if the database is empty
        if (customerRepository.count() == 0) {
            System.out.println(" 0 records at the database found");
            CustomerFaker customerFaker = new CustomerFaker();
            int qty = 50;
            customerRepository.saveAll(customerFaker.GetNObjects(qty));
            System.out.println(qty + " fake customers have been created and saved to the database.");
        }
    }
}