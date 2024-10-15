package dev.example.restaurantManager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {
    // Add a serialVersionUID
    private static final long serialVersionUID = 1L;

    private String name;
    private Double price;
    private String content;
    private boolean active;
    private boolean water;

}

