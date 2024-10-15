package dev.example.restaurantManager.model;

import java.util.ArrayList;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {


    private String id;
    private Date date;
    private String waiter;
    private int peopleQty;
    private double totalPayment;
    private boolean paid;
    private ArrayList<Menu> menus = new ArrayList<>();

    @Override
    public String toString() {
        return
                "date: " + date + "\n"  +
                "waiter: " + waiter  + "\n"  +
                "peopleQty: " + peopleQty +"\n"  +
                "totalPayment: " + totalPayment +" euros\n"  +
                "paid: " + paid +"\n"  +
                "Menus quantity: " + menus.size() +"\n"  +
                "menus: " + menus
                ;
    }

}