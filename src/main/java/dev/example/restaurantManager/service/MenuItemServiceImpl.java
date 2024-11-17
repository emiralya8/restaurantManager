package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuItem;
import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    MenuItemRepository menuItemRepository;

    @Override
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    @Override
    public MenuItem createMenuItems(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    @Override
    public MenuItem getMenuItemsById(String id) {
        return menuItemRepository.findById(id).orElse(null);
    }

    @Override
    public MenuItem updateMenuItems(String id, MenuItem menuItemDetails) {
        boolean isTrue = false;
        MenuItem menuItemElement = menuItemRepository.findById(id).orElse(null);
        if(menuItemElement != null){
            Field[] fields = menuItemRepository.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    if(field.get(menuItemElement) != field.get(menuItemDetails)){
                        isTrue = true;
                        field.set(menuItemElement,field.get(menuItemDetails));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            menuItemRepository.save(menuItemElement);
        }

        return isTrue ? menuItemElement : menuItemDetails;
    }

    @Override
    public boolean deleteMenuItems(String id) {
        Optional<MenuItem> menuItemElement = menuItemRepository.findById(id);
        if(menuItemElement != null){
            menuItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public long countMenuItems() {
        return menuItemRepository.count();
    }
}
