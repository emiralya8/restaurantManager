package dev.example.restaurantManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MenuRestaurant  {

    @Id
    private String id;
    private String name;
    private Double price;
    private String content;
    private boolean active;
    private boolean water;

    @ManyToMany(mappedBy = "menus", fetch = FetchType.LAZY)
    private List<OrderRestaurant> orders = new ArrayList<>();

    //using bidirectional manytomany
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(
//            name = "MENU_RESTAURANT_MENU_ITEM",
//            joinColumns = @JoinColumn(name = "MENU_RESTAURANT_ID_FK"),
//            inverseJoinColumns = @JoinColumn(name = "MENU_ITEM_ID_FK")
//    )
    private List<MenuItem> menuItems = new ArrayList<>();

    public MenuRestaurant(String id, String name, Double price, String content, boolean active, boolean water) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.content = content;
        this.active = active;
        this.water = water;
    }

    //We  might want to exclude 'orders' from toString() to avoid circular references
    @Override
    public String toString() {
        return "MenuRestaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", content='" + content + '\'' +
                ", active=" + active +
                ", water=" + water +
                ", ordersCount=" + (orders != null ? orders.size() : 0) +
                ", orders=" + orders +
                '}';
    }

}

