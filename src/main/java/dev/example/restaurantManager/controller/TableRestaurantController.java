package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/table")
@RestController
public class TableRestaurantController {
    @Autowired
    private IService<TableRestaurant> tableService;

    @GetMapping("/allCustomers")
    public List<TableRestaurant> getAllTables() {
        return tableService.getAllElements();
    }

    @PostMapping
    public TableRestaurant createTable(@RequestBody TableRestaurant tableRestaurant) {
        return tableService.createElement(tableRestaurant);
    }

    @PutMapping("/{id}")
    public TableRestaurant updateTable(@PathVariable String id, @RequestBody TableRestaurant tableRestaurantDetails) {
        return tableService.updateElement(id, tableRestaurantDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteTable(@PathVariable String id) {
        tableService.deleteElement(id);
    }
}
