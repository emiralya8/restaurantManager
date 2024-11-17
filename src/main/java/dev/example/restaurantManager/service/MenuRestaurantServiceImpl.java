package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class MenuRestaurantServiceImpl implements MenuRestaurantService{

    @Autowired
    MenuRestaurantRepository menuRestaurantRepository;

    @Override
    public List<MenuRestaurant> getAllMenuRestaurants() {
        return menuRestaurantRepository.findAll();
    }

    @Override
    public MenuRestaurant createMenuRestaurants(MenuRestaurant menuRestaurant) {
        return menuRestaurantRepository.save(menuRestaurant);
    }

    @Override
    public MenuRestaurant getMenuRestaurantsById(String id) {
        return menuRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public MenuRestaurant updateMenuRestaurants(String id, MenuRestaurant menuRestaurantDetails) {
        boolean isTrue = false;
        MenuRestaurant menuRestaurantElement = menuRestaurantRepository.findById(id).orElse(null);
        if(menuRestaurantElement != null){
            Field[] fields = menuRestaurantElement.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    if(field.get(menuRestaurantElement) != field.get(menuRestaurantDetails)){
                        isTrue = true;
                        field.set(menuRestaurantElement,field.get(menuRestaurantDetails));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            menuRestaurantRepository.save(menuRestaurantElement);
        }

        return isTrue ? menuRestaurantElement : menuRestaurantDetails;
    }

    @Override
    public boolean deleteMenuRestaurants(String id) {
        Optional<MenuRestaurant> menuRestaurantElement = menuRestaurantRepository.findById(id);
        if(menuRestaurantElement != null){
            menuRestaurantRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
