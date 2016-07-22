package com.example.restaurant.domain.controller;

import com.example.restaurant.domain.Booking;
import com.example.restaurant.domain.service.BookingService;
import com.example.restaurant.domain.valueobject.BookingVO;
import com.google.common.collect.Iterables;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by hector on 22/07/16.
 */
@RestController
@RequestMapping("/v1/bookings")
public class BookingController {

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    protected BookingService bookingService;

    public BookingController() {
    }

    public BookingController(BookingService userService) {
        this.bookingService = userService;
    }

    /**
     * Fetch restaurants with the specified name. A partial case-insensitive
     * match is supported. So <code>http://.../restaurants/rest</code> will find
     * any restaurants with upper or lower case 'rest' in their name.
     *
     * @param name
     * @return A non-null, non-empty collection of restaurants.
     */
    public ResponseEntity<Iterable<Booking>> findByName(@RequestParam("name") String name) {
        logger.info(String.format("booking-service findByName() invoked:{} for {} ",
                bookingService.getClass().getName(), name));

        name = name.trim().toLowerCase();
        Iterable<Booking> bookings;
        try {
            bookings = bookingService.findByName(name);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised findByName REST Call", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return !Iterables.isEmpty(bookings) ? new ResponseEntity<>(bookings, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Fetch restaurants with the given id.
     * <code>http://.../v1/restaurants/{restaurant_id}</code> will return
     * restaurant with given id.
     *
     * @param id
     * @return A non-null, non-empty collection of restaurants.
     */
    @RequestMapping(value = "/{booking_id}", method = RequestMethod.GET)
    public ResponseEntity<Booking> findById(@PathVariable("booking_id") String id) {

        logger.info(String.format("booking-service findById() invoked:{} for {} ", bookingService.getClass().getName(), id));
        Long bookingId = 0L;

        if (StringUtils.isNumeric(id)) {
            bookingId = Long.valueOf(id);
        }
        Booking booking;
        try {
            booking = bookingService.getBookingById(bookingId);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception raised findById REST Call", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return booking != null ? new ResponseEntity<>(booking, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Add restaurant with the specified information.
     *
     * @param bookingVO
     * @return A non-null restaurant.
     * @throws RestaurantNotFoundException If there are no matches at all.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Booking> add(@RequestBody BookingVO bookingVO) {

        logger.info(String.format("booking-service add() invoked: %s for %s", bookingService.getClass().getName(), bookingVO.getName()));

        Booking booking = new Booking();
        BeanUtils.copyProperties(bookingVO, booking);
        try {
            this.bookingService.saveBooking(booking);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised add User REST Call " + ex);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

