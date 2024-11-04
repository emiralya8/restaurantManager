package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.TableRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableRestaurantServiceImpl implements TableRestaurantService {

    @Autowired
    TableRestaurantRepository tableRestaurantRepository;

    @Override
    public List<TableRestaurant> getAllTableRestaurant() {
        return tableRestaurantRepository.findAll();
    }

    @Override
    public TableRestaurant createTableRestaurant(TableRestaurant tableRestaurant) {
        return tableRestaurantRepository.save(tableRestaurant);
    }

    @Override
    public TableRestaurant getTableRestaurantById(String id) {
        Optional<TableRestaurant> tableRestaurant = tableRestaurantRepository.findById(id);
        return tableRestaurant.orElse(null);
    }

    @Override
    public TableRestaurant updateTableRestaurant(String id, TableRestaurant tableRestaurant) {
        TableRestaurant updateTableRestaurant = tableRestaurantRepository.findById(id).orElse(null);

        if(tableRestaurant != null){
            tableRestaurant.setId(id);
            tableRestaurant.setName(updateTableRestaurant.getName());
            tableRestaurant.setDescription(updateTableRestaurant.getDescription());
            tableRestaurant.setQty(updateTableRestaurant.getQty());
            tableRestaurant.setBusy(updateTableRestaurant.isBusy());
            tableRestaurant.setBookings(updateTableRestaurant.getBookings());
            tableRestaurant.setEatInOrderRestaurant(updateTableRestaurant.getEatInOrderRestaurant());
            return tableRestaurantRepository.save(tableRestaurant);
        }

        return null;
    }

    @Override
    public boolean deleteTableRestaurant(String id) {
        tableRestaurantRepository.deleteById(id);
        return tableRestaurantRepository.findById(id).isPresent();
    }
}
