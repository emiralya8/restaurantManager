package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.service.MenuRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuRestaurantController {

    @Autowired
    private MenuRestaurantService menuRestaurantService;

    @GetMapping
    public ResponseEntity<List<MenuRestaurant>> getAllMenus() {
        List<MenuRestaurant> menus = menuRestaurantService.getAllMenus();
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MenuRestaurant> createMenu(@RequestBody MenuRestaurant menu) {
        MenuRestaurant newMenu = menuRestaurantService.createMenu(menu);
        return new ResponseEntity<>(newMenu, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuRestaurant> getMenuById(@PathVariable String id) {
        MenuRestaurant menu = menuRestaurantService.getMenuById(id);
        if (menu != null) {
            return new ResponseEntity<>(menu, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuRestaurant> updateMenu(@PathVariable String id, @RequestBody MenuRestaurant menuDetails) {
        MenuRestaurant updatedMenu = menuRestaurantService.updateMenu(id, menuDetails);
        if (updatedMenu != null) {
            return new ResponseEntity<>(updatedMenu, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable String id) {
        boolean deleted = menuRestaurantService.deleteMenu(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countMenus() {
        long count = menuRestaurantService.countMenus();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @PostMapping("/{menuId}/items/{menuItemId}")
    public ResponseEntity<MenuRestaurant> addMenuItemToMenu(@PathVariable String menuId, @PathVariable String menuItemId) {
        MenuRestaurant updatedMenu = menuRestaurantService.addMenuItemToMenu(menuId, menuItemId);
        if (updatedMenu != null) {
            return new ResponseEntity<>(updatedMenu, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{menuId}/items/{menuItemId}")
    public ResponseEntity<MenuRestaurant> removeMenuItemFromMenu(@PathVariable String menuId, @PathVariable String menuItemId) {
        MenuRestaurant updatedMenu = menuRestaurantService.removeMenuItemFromMenu(menuId, menuItemId);
        if (updatedMenu != null) {
            return new ResponseEntity<>(updatedMenu, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}