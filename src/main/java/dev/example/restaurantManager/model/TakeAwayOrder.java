package dev.example.restaurantManager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TakeAwayOrder extends  Order {

    private Customer customerTakeAway;

    // Constructor for TakeAwayOrder with the WHOLE DATA fields
    public TakeAwayOrder(String id, Date date, String waiter, int peopleQty,
                         double totalPayment, boolean paid, ArrayList<Menu> menus,
                         Customer customerTakeAway) {

        // THIS PART is SUPERCLASS
        super(id, date, waiter, peopleQty, totalPayment, paid, menus);
        // THIS PART is SUBCLASS
        this.customerTakeAway = customerTakeAway;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Type: Take Away\n" +
                "Customer: " + customerTakeAway;
    }
}
