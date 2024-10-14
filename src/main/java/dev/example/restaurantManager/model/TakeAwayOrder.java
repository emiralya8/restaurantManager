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

    private String nameCustomerTakeAway;

    public TakeAwayOrder(String id, Date date, String waiter, int peopleQty,
                         double totalPayment, boolean paid, ArrayList<Menu> menus,
                         String nameCustomerTakeAway) {
        super(id, date, waiter, peopleQty, totalPayment, paid, menus);
        this.nameCustomerTakeAway = nameCustomerTakeAway;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Type: Take Away\n" +
                "Customer Name: " + nameCustomerTakeAway;
    }
}
