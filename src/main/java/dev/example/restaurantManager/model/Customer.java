package dev.example.restaurantManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private int age;
    private boolean vipCustomer;
    private boolean deleted;

    // annotations
    //@OneToMany(mappedBy = "customerTakeAway", cascade = CascadeType.ALL)
    //private ArrayList<TakeAwayOrder> takeAwayOrders = new ArrayList<>();

}