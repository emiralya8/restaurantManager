package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.repository.IMenuRepository;
import dev.example.restaurantManager.repository.MenuEntityManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService implements IService<MenuRestaurant>, ICustomQueriesService<MenuRestaurant>{

    @Autowired
    private IMenuRepository menuRepository;

    @Autowired
    private MenuEntityManagerRepository alternativeMenuRepository;

    @Override
    public List<MenuRestaurant> getAllElements() {
        return menuRepository.findAll();
    }

    @Override
    public MenuRestaurant createElement(MenuRestaurant element) {
        return menuRepository.save(element);
    }

    @Override
    public MenuRestaurant getElementById(String id) {
        return menuRepository.findById(id).orElse(null);
    }

    @Override
    public MenuRestaurant updateElement(String id, MenuRestaurant eDetails) {
        boolean isTrue = false;
        MenuRestaurant menuElement = menuRepository.findById(id).orElse(null);
        if(menuElement != null){
            Field[] fields = menuElement.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    if(field.get(menuElement) != field.get(eDetails)){
                        isTrue = true;
                        field.set(menuElement,field.get(eDetails));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            menuRepository.save(menuElement);
        }

        return isTrue ? menuElement : eDetails;
    }

    @Override
    public boolean deleteElement(String id) {
        menuRepository.deleteById(id);
        Optional<MenuRestaurant> menu = menuRepository.findById(id);
        return menu.isEmpty()
                ? false : true ;
    }

    @Override
    public long countElements() {
        return 0;
    }

    @Override
    public List<MenuRestaurant> getElementByContentDescription(String contentDescription) {
        return menuRepository.findByContent(contentDescription);
    }
}
