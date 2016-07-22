package com.example.restaurant.domain.service;

import com.example.restaurant.domain.Booking;

import java.util.List;
import java.util.Map;

/**
 * Created by hector on 22/07/16.
 */
public interface BookingService {

    Iterable<Booking> getAllBookings();

    Booking getBookingById(Long id);

    Booking saveBooking(Booking booking);

    void removeBooking(Booking booking);

    Iterable<Booking> findByName(String name);

    Iterable<Booking> findByCriteria(Map<String, List<String>> name);

}
