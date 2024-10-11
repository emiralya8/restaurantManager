package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.ITableRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class TableRestaurantService implements IService<TableRestaurant>{

    @Autowired
    private ITableRestaurantRepository tableRepository;

    @Override
    public List<TableRestaurant> getAllElements() {
        return tableRepository.findAll();
    }

    @Override
    public TableRestaurant createElement(TableRestaurant element) {
        return tableRepository.save(element);
    }

    @Override
    public TableRestaurant getElementById(String id) {
        return tableRepository.findById(id).orElse(null);
    }

    @Override
    public TableRestaurant updateElement(String id, TableRestaurant eDetails) {
        boolean isTrue = false;
        TableRestaurant tableRestaurantElement = tableRepository.findById(id).orElse(null);
        if(tableRestaurantElement != null){
            Field[] fields = tableRestaurantElement.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    if(field.get(tableRestaurantElement) != field.get(eDetails)){
                        isTrue = true;
                        field.set(tableRestaurantElement,field.get(eDetails));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            tableRepository.save(tableRestaurantElement);
        }

        return isTrue ? tableRestaurantElement : eDetails;
    }

    @Override
    public boolean deleteElement(String id) {
        tableRepository.deleteById(id);
        Optional<TableRestaurant> table = tableRepository.findById(id);
        return table.isEmpty()
                ? false : true ;
    }

    @Override
    public long countElements() {
        return 0;
    }
}
