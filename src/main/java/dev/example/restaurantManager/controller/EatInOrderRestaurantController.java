package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.EatInOrderRestaurant;
import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.service.EatInOrderRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/api/v1/EatInOrderRestaurant")
@RestController
public class EatInOrderRestaurantController {

    @Autowired
    EatInOrderRestaurantService eatInOrderRestaurantService;

    @GetMapping("/allEatInOrderRestaurant")
    public ResponseEntity<List<EatInOrderRestaurant>> getAllTableRestaurants(){
        List<EatInOrderRestaurant> lstEatInRestaurant = eatInOrderRestaurantService.getAllEatInOrderRestaurant();
        HttpHeaders headers = this.getCommonHeaders("Eat in restaurant");
        return lstEatInRestaurant != null
                ? new ResponseEntity<>(lstEatInRestaurant, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<EatInOrderRestaurant> saveTableRestaurant(@RequestBody
                                                               EatInOrderRestaurant eatInOrderRestaurant){
        HttpHeaders headers = this.getCommonHeaders("Eat in restaurant");
        EatInOrderRestaurant newEatInOrderRestaurant = eatInOrderRestaurantService.createEatInOrderRestaurant(eatInOrderRestaurant);
        return newEatInOrderRestaurant != null
                ? new ResponseEntity<>(newEatInOrderRestaurant, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EatInOrderRestaurant> updateTableRestaurant(@PathVariable String id, @RequestBody EatInOrderRestaurant eatInOrderRestaurantDetails) {
        EatInOrderRestaurant updatedeatInOrderRestaurant = eatInOrderRestaurantService.updateEatInOrderRestaurant(id, eatInOrderRestaurantDetails);
        HttpHeaders headers = getCommonHeaders("Update a table restaurant");

        return updatedeatInOrderRestaurant != null
                ? new ResponseEntity<>(updatedeatInOrderRestaurant, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTableRestaurant(@PathVariable String id) {
        boolean deleted = eatInOrderRestaurantService.deleteEatInOrderRestaurant(id);
        HttpHeaders headers = getCommonHeaders("Delete a eat in order restaurant");
        headers.add("deleted", String.valueOf(deleted));

        return deleted
                ? new ResponseEntity<>(headers, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EatInOrderRestaurant> getEatInOrderRestaurantById(@PathVariable String id) {
        EatInOrderRestaurant eatInOrderRestaurant = eatInOrderRestaurantService.getEatInOrderRestaurantById(id);
        HttpHeaders headers = getCommonHeaders("Get a eat in order restaurant by Id");

        return eatInOrderRestaurant != null
                ? new ResponseEntity<>(eatInOrderRestaurant, headers, HttpStatus.OK)
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
