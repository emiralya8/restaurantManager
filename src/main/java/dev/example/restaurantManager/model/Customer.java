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
    
    @OneToMany(mappedBy = "customerMapped", cascade = CascadeType.ALL)
    private ArrayList<Booking> bookings;

    public Customer(String number, String alex, String mail, String number1,
                    int i, boolean b, boolean b1) {
    }

    //method to add
    public void addBooking(Booking booking) {
        this.getBookings().add(booking);
        if (booking.getCustomerMapped() != null)
            booking.getCustomerMapped().getBookings().remove(booking);
        booking.setCustomerMapped(this);
    }
    
    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                "name: " + name + "\n"  +
                "email: " + email + "\n"  +
                "phoneNumber: " + phoneNumber + "\n"  +
                "age: " + age +"\n"  +
                "vipCustomer: " + vipCustomer
                ;
    }
}