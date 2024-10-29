package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.TableRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TableRestaurantServiceImpl implements TableRestaurantService {

    @Autowired
    private TableRestaurantRepository tableRestaurantRepository;

    @Override
    public List<TableRestaurant> getAllTables() {
        return tableRestaurantRepository.findAll();
    }

    @Override
    public TableRestaurant createTable(TableRestaurant table) {
        table.setId(UUID.randomUUID().toString());
        return tableRestaurantRepository.save(table);
    }

    @Override
    public TableRestaurant getTableById(String id) {
        return tableRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public TableRestaurant updateTable(String id, TableRestaurant tableDetails) {
        TableRestaurant table = tableRestaurantRepository.findById(id).orElse(null);
        if (table != null) {
            table.setName(tableDetails.getName());
            table.setDescription(tableDetails.getDescription());
            table.setQty(tableDetails.getQty());
            table.setBusy(tableDetails.isBusy());
            return tableRestaurantRepository.save(table);
        }
        return null;
    }

    @Override
    public boolean deleteTable(String id) {
        Optional<TableRestaurant> table = tableRestaurantRepository.findById(id);
        if (table.isPresent()) {
            tableRestaurantRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public long countTables() {
        return tableRestaurantRepository.count();
    }
}