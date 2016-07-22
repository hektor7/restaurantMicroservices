package com.example.restaurant.domain.service.impl;

import com.example.restaurant.domain.Booking;
import com.example.restaurant.domain.repository.BookingRepository;
import com.example.restaurant.domain.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by hector on 22/07/16.
 */
@Service("bookingService")
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    public BookingServiceImpl() {
    }

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    @Override
    public Iterable<Booking> getAllBookings() {
        return this.bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        return this.bookingRepository.findOne(id);
    }

    @Override
    public Booking saveBooking(Booking booking) {
        return this.bookingRepository.save(booking);
    }

    @Override
    public void removeBooking(Booking booking) {
        this.bookingRepository.delete(booking);
    }

    @Override
    public Iterable<Booking> findByName(String name) {
        return this.bookingRepository.findByName(name);
    }

    @Override
    public Iterable<Booking> findByCriteria(Map<String, List<String>> name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
