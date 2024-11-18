package dev.example.restaurantManager.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMenuQty {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private long qty;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private OrderRestaurant orderRestaurant;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    @JsonIgnore
    private MenuRestaurant menuRestaurant;

    public OrderMenuQty(long qty, OrderRestaurant orderRestaurant, MenuRestaurant menuRestaurant) {
        this.qty = qty;
        this.orderRestaurant = orderRestaurant;
        this.menuRestaurant = menuRestaurant;
    }

    public String getMenuName() {
        return menuRestaurant.getName();
    }

    @Override
    public String toString() {
        return "OrderMenuQty{" +
                "id:'" + id + '\'' +
                ", qty:" + qty +
                ", order:" + orderRestaurant.getId() +
                ", menu:" + menuRestaurant.getName() +
                '}';
    }
}
