package dev.example.restaurantManager.model;

import java.util.ArrayList;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Date date;
    private String waiter;
    private int peopleQty;
    private double totalPayment;
    private boolean paid;
    private ArrayList<TableRestaurant> tableRestaurants;
    private ArrayList<Menu> menus;

    @Override
    public String toString() {
        return
                "date: " + date + "\n"  +
                "waiter: " + waiter  + "\n"  +
                "peopleQty: " + peopleQty +"\n"  +
                "totalPayment: " + totalPayment +" euros\n"  +
                "paid: " + paid +"\n"  +
                "Tables quantity: " + tableRestaurants.size() +"\n"  +
                "table: " + tableRestaurants +"\n"  +
                "Menus quantity: " + menus.size() +"\n"  +
                "menus: " + menus
                ;
    }

}