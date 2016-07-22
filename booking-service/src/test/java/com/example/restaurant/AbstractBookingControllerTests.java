package com.example.restaurant;

import com.example.restaurant.domain.Booking;
import com.example.restaurant.domain.controller.BookingController;
import com.example.restaurant.domain.valueobject.BookingVO;
import com.google.common.collect.Iterables;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.logging.Logger;

/**
 * Created by hector on 22/07/16.
 */
public abstract class AbstractBookingControllerTests {

    protected static final Long BOOKING = 1L;
    protected static final String BOOKING_NAME = "Booking 1";
    protected static final Long BOOKING_RESTAURANT_ID = 1L;
    protected static final Long BOOKING_TABLE_ID = 1L;
    protected static final Long BOOKING_USER_ID = 1L;

    @Autowired
    BookingController bookingController;

    @Test
    public void validBookingById() {
        Logger.getGlobal().info("Start validBookingById test");
        ResponseEntity<Booking> booking = bookingController.findById(BOOKING.toString());

        Assert.assertEquals(HttpStatus.OK, booking.getStatusCode());
        Assert.assertTrue(booking.hasBody());
        Assert.assertNotNull(booking.getBody());
        Assert.assertEquals(BOOKING, booking.getBody().getId());
        Assert.assertEquals(BOOKING_NAME, booking.getBody().getName());
        Logger.getGlobal().info("End validBookingById test");
    }

    @Test
    public void validUserByName() {
        Logger.getGlobal().info("Start validBookingByName test");
        ResponseEntity<Iterable<Booking>> bookings = bookingController.findByName(BOOKING_NAME);
        Logger.getGlobal().info("In validAccount test");

        Assert.assertEquals(HttpStatus.OK, bookings.getStatusCode());
        Assert.assertTrue(bookings.hasBody());
        Assert.assertNotNull(bookings.getBody());
        Assert.assertFalse(Iterables.isEmpty(bookings.getBody()));
        Booking restaurant = bookings.getBody().iterator().next();
        Assert.assertEquals(BOOKING, restaurant.getId());
        Assert.assertEquals(BOOKING_NAME, restaurant.getName());
        Logger.getGlobal().info("End validBookingByName test");
    }

    @Test
    public void validAdd() {
        Logger.getGlobal().info("Start validAdd test");
        BookingVO bookingVO = new BookingVO();
        bookingVO.setId("999");
        bookingVO.setName("Test Restaurant");

        ResponseEntity<Booking> restaurants = bookingController.add(bookingVO);
        Assert.assertEquals(HttpStatus.CREATED, restaurants.getStatusCode());
        Logger.getGlobal().info("End validAdd test");
    }
}
