package dev.example.restaurantManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TakeAwayOrder extends OrderRestaurant {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_TA_FK_ID")
    private Customer customerTakeAway;

    // Constructor for TakeAwayOrder with the WHOLE DATA fields
    public TakeAwayOrder(String id, Date date, String waiter, int peopleQty,
                         double totalPayment, boolean paid, List<OrderMenuQty> orderMenuQties,
                         Customer customerTakeAway) {
        super(id, date, waiter, peopleQty, totalPayment, paid);
        this.setOrderMenuQties(orderMenuQties);
        this.customerTakeAway = customerTakeAway;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Type: Take Away\n" +
                "Customer: " + customerTakeAway;
    }
}