package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.service.TableRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/api/v1/tableRestaurant")
@RestController
public class TableRestaurantController {

    @Autowired
    TableRestaurantService tableRestaurantService;

    @GetMapping("/allTableRestaurants")
    public ResponseEntity<List<TableRestaurant>> getAllTableRestaurants(){
        HttpHeaders headers = this.getCommonHeaders("Table Restaurant");
        List<TableRestaurant> lstTableRestaurant = tableRestaurantService.getAllTableRestaurant();
        return lstTableRestaurant != null
                ? new ResponseEntity<>(lstTableRestaurant, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<TableRestaurant> saveTableRestaurant(@RequestBody
    TableRestaurant tableRestaurant){
        HttpHeaders headers = this.getCommonHeaders("Table Restaurant");
        TableRestaurant newTableRestaurant = tableRestaurantService.createTableRestaurant(tableRestaurant);
        return newTableRestaurant != null
                ? new ResponseEntity<>(newTableRestaurant, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TableRestaurant> updateTableRestaurant(@PathVariable String id, @RequestBody TableRestaurant tableRestaurantDetails) {
        TableRestaurant updatedTableRestaurant = tableRestaurantService.updateTableRestaurant(id, tableRestaurantDetails);
        HttpHeaders headers = getCommonHeaders("Update a table restaurant");

        return updatedTableRestaurant != null
                ? new ResponseEntity<>(updatedTableRestaurant, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTableRestaurant(@PathVariable String id) {
        boolean deleted = tableRestaurantService.deleteTableRestaurant(id);
        HttpHeaders headers = getCommonHeaders("Delete a table restaurant");
        headers.add("deleted", String.valueOf(deleted));

        return deleted
                ? new ResponseEntity<>(headers, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableRestaurant> getTableRestaurantById(@PathVariable String id) {
        TableRestaurant tableRestaurant = tableRestaurantService.getTableRestaurantById(id);
        HttpHeaders headers = getCommonHeaders("Get a table restaurant by Id");

        return tableRestaurant != null
                ? new ResponseEntity<>(tableRestaurant, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    private HttpHeaders getCommonHeaders(String description) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", description);
        headers.add("content-type", "application/json");
        headers.add("date", new Date().toString());
        headers.add("server", "H2 Database");
        headers.add("version", "1.0.0");
        return headers;
    }
}
