package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Booking;
import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.BookingRepository;
import dev.example.restaurantManager.repository.CustomerRepository;
import dev.example.restaurantManager.repository.TableRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TableRestaurantRepository tableRestaurantRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking createBooking(Booking booking, String tableId, String customerId) {
        Optional<TableRestaurant> tableOptional = tableRestaurantRepository.findById(tableId);
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (tableOptional.isPresent() && customerOptional.isPresent()) {
            TableRestaurant table = tableOptional.get();
            Customer customer = customerOptional.get();

            booking.setId(UUID.randomUUID().toString());
            booking.setTableRestaurantMapped(table);
            booking.setCustomerMapped(customer);

            return bookingRepository.save(booking);
        } else {
            throw new IllegalArgumentException("Invalid table or customer ID");
        }
    }

    @Override
    public Booking createBooking(Booking booking) {
        if (booking.getTableRestaurantMapped() == null || booking.getCustomerMapped() == null) {
            throw new IllegalArgumentException("Booking must have both a table and a customer");
        }

        Optional<TableRestaurant> tableOptional = tableRestaurantRepository.findById(booking.getTableRestaurantMapped().getId());
        Optional<Customer> customerOptional = customerRepository.findById(booking.getCustomerMapped().getId());

        if (tableOptional.isPresent() && customerOptional.isPresent()) {
            TableRestaurant table = tableOptional.get();
            Customer customer = customerOptional.get();

            booking.setId(UUID.randomUUID().toString());
            booking.setTableRestaurantMapped(table);
            booking.setCustomerMapped(customer);

            return bookingRepository.save(booking);
        } else {
            throw new IllegalArgumentException("Invalid table or customer ID");
        }
    }

    @Override
    public Booking getBookingById(String id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public Booking updateBooking(String id, Booking bookingDetails) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setPeopleQty(bookingDetails.getPeopleQty());
            booking.setDate(bookingDetails.getDate());
            booking.setBookingDate(bookingDetails.getBookingDate());
            booking.setShift(bookingDetails.getShift());
            return bookingRepository.save(booking);
        }
        return null;
    }

    @Override
    public boolean deleteBooking(String id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public long countBookings() {
        return bookingRepository.count();
    }
}