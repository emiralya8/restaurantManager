package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.service.ICustomQueriesService;
import dev.example.restaurantManager.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/menu")
@RestController
public class MenuController {
    @Autowired
    private IService<MenuRestaurant> menuService;

    @Autowired
    private ICustomQueriesService<MenuRestaurant> menuCustomQueriesService;

    @GetMapping("/allMenus")
    public List<MenuRestaurant> getAllMenus() {
        return menuService.getAllElements();
    }

    @PostMapping
    public MenuRestaurant createMenu(@RequestBody MenuRestaurant menu) {
        return menuService.createElement(menu);
    }

    @PutMapping("/{id}")
    public MenuRestaurant updateMenu(@PathVariable String id, @RequestBody MenuRestaurant menuDetails) {
        return menuService.updateElement(id, menuDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteMenu(@PathVariable String id) {
        menuService.deleteElement(id);
    }

    @GetMapping("/allMenus/{content}")
    public List<MenuRestaurant> getAllMenusByContent(@PathVariable String content) {
        return menuCustomQueriesService.getElementByContentDescription(content);
    }

}
