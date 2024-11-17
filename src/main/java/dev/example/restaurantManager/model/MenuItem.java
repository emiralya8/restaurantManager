package dev.example.restaurantManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.example.restaurantManager.model.enums.CourseType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;
    private String description;
    private double price;

    @Enumerated(EnumType.STRING)
    private CourseType courseType;

    //using manytomany bidirectional way and remove recursion issue.
    @JsonIgnore
    @ManyToMany(mappedBy = "menuItems", fetch = FetchType.EAGER)
    private List<MenuRestaurant> menus = new ArrayList<>();

    // Default constructor
    public MenuItem() {
        this.id = UUID.randomUUID().toString();
    }

    // Constructors, getters, and setters

    public MenuItem(String name, String description, double price) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
