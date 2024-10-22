package dev.example.restaurantManager.repository;

import org.springframework.stereotype.Component;

@Component
public class RepositoryManagerFactory {
    private final ICustomerRepository customerRepository;
    private final IMenuRepository menuRestaurantRepository;
    private final ITableRestaurantRepository tableRestaurantRepository;

    public RepositoryManagerFactory(ICustomerRepository customerRepository, IMenuRepository menuRestaurantRepository, ITableRestaurantRepository tableRestaurantRepository){
        this.customerRepository = customerRepository;
        this.menuRestaurantRepository = menuRestaurantRepository;
        this.tableRestaurantRepository = tableRestaurantRepository;
    }

    public Object getRepository(Object object){

        String strName = object.getClass().getName().substring(object.getClass().getName().lastIndexOf(".")+1).toString();
        switch (strName){
            case "Customer":
                return customerRepository;
            case "MenuRestaurant":
                return menuRestaurantRepository;
            default:
                return tableRestaurantRepository;
        }
    }
}
