package dev.example.restaurantManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MenuRestaurant  {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;
    private Double price;
    private String content;
    private boolean active;
    private boolean water;

    @ManyToMany(mappedBy = "menus", fetch = FetchType.LAZY)
    private List<OrderRestaurant> orders = new ArrayList<>();

    //using bidirectional manytomany
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "menu_item_restaurant",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_item_id"))
    private List<MenuItem> menuItems = new ArrayList<>();

    public MenuRestaurant(String id, String name, Double price, String content, boolean active, boolean water) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.content = content;
        this.active = active;
        this.water = water;
    }

    public void addMenuItem(MenuItem menuItem) {
        this.menuItems.add(menuItem);
        if (menuItem.getMenus() == null) {
            menuItem.setMenus(new ArrayList<>());
        }
        if (!menuItem.getMenus().contains(this)) {
            menuItem.getMenus().add(this);
        }
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

