package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Booking;

import java.util.List;

public class BookingServiceImpl implements BookingService{
    @Override
    public List<Booking> getAllBookings() {
        return List.of();
    }

    @Override
    public Booking createBooking(Booking booking) {
        return null;
    }

    @Override
    public Booking getBookingById(String id) {
        return null;
    }

    @Override
    public Booking updateBooking(String id, Booking bookingDetails) {
        return null;
    }

    @Override
    public boolean deleteBooking(String id) {
        return false;
    }
}
