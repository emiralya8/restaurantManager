package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.service.TableRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class TableRestaurantController {

    @Autowired
    private TableRestaurantService tableRestaurantService;

    @GetMapping
    public ResponseEntity<List<TableRestaurant>> getAllTables() {
        List<TableRestaurant> tables = tableRestaurantService.getAllTables();
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TableRestaurant> createTable(@RequestBody TableRestaurant table) {
        TableRestaurant newTable = tableRestaurantService.createTable(table);
        return new ResponseEntity<>(newTable, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableRestaurant> getTableById(@PathVariable String id) {
        TableRestaurant table = tableRestaurantService.getTableById(id);
        if (table != null) {
            return new ResponseEntity<>(table, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TableRestaurant> updateTable(@PathVariable String id, @RequestBody TableRestaurant tableDetails) {
        TableRestaurant updatedTable = tableRestaurantService.updateTable(id, tableDetails);
        if (updatedTable != null) {
            return new ResponseEntity<>(updatedTable, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable String id) {
        boolean deleted = tableRestaurantService.deleteTable(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countTables() {
        long count = tableRestaurantService.countTables();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}