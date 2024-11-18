package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.model.OrderMenuQty;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuRestaurantServiceImpl implements MenuRestaurantService {

    @Autowired
    private MenuRestaurantRepository menuRestaurantRepository;

    @Override
    public List<MenuRestaurant> getAllMenus() {
        List<MenuRestaurant> lstMenuRestaurant = menuRestaurantRepository.findAll().stream().collect(Collectors.toList());
        for (MenuRestaurant m : lstMenuRestaurant){
            List<OrderMenuQty> lstOrderMenuQty = new ArrayList<>();
            for (OrderMenuQty o : m.getOrdersQty().stream().collect(Collectors.toList())){
                lstOrderMenuQty.add(o);
                m.setOrdersQty(lstOrderMenuQty);
                //o.getOrderRestaurant()
            }
        }
        return lstMenuRestaurant;
        //return menuRestaurantRepository.findAll();
    }

    @Override
    public MenuRestaurant getMenuById(String id) {
        return menuRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public MenuRestaurant createMenu(MenuRestaurant menuRestaurant) {
        return menuRestaurantRepository.save(menuRestaurant);
    }

    @Override
    public MenuRestaurant updateMenu(String id, MenuRestaurant menuRestaurantDetails) {
        boolean isTrue = false;
        MenuRestaurant menuRestaurantElement = menuRestaurantRepository.findById(id).orElse(null);
        if(menuRestaurantElement != null){
            Field[] fields = menuRestaurantDetails.getClass().getDeclaredFields();
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
    public boolean deleteMenu(String id) {
        Optional<MenuRestaurant> menuRestaurantElement = menuRestaurantRepository.findById(id);
        if(menuRestaurantElement != null){
            menuRestaurantRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public long countMenus() {
        return menuRestaurantRepository.count();
    }
}
