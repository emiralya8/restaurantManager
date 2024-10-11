package dev.example.restaurantManager.utilities;

import dev.example.restaurantManager.repository.ICustomerRepository;
import dev.example.restaurantManager.repository.IMenuRepository;
import dev.example.restaurantManager.repository.ITableRestaurantRepository;
import dev.example.restaurantManager.utilities.fakers.CustomerFaker;
import dev.example.restaurantManager.utilities.fakers.MenuFaker;
import dev.example.restaurantManager.utilities.fakers.TableRestaurantFaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResourcesDataLoader {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private ITableRestaurantRepository tableRepository;

    @Autowired
    private IMenuRepository menuRepository;

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

    public void createFakeTables() {
        // Check if the database is empty
        System.out.println(" 0 records at the database found");
        TableRestaurantFaker tableRestaurantFaker = new TableRestaurantFaker();
        int qty = 50;
        tableRepository.saveAll(tableRestaurantFaker.GetNObjects(qty));
        System.out.println(qty + " fake tables have been created and saved to the database.");
        /*
        int i = 0;
        i = (int)tableRepository.count();
        if (tableRepository.count() == 0) {
            System.out.println(" 0 records at the database found");
            TableFaker tableFaker = new TableFaker();
            int qty = 50;
            tableRepository.saveAll(tableFaker.GetNObjects(qty));
            System.out.println(qty + " fake tables have been created and saved to the database.");
        }

         */
    }

    public void createFakeMenus() {
        // Check if the database is empty
        if (menuRepository.count() == 0) {
            System.out.println(" 0 records at the database found");
            MenuFaker menuFaker = new MenuFaker();
            int qty = 50;
            menuRepository.saveAll(menuFaker.GetNObjects(qty));
            System.out.println(qty + " fake menus have been created and saved to the database.");
        }
    }
}