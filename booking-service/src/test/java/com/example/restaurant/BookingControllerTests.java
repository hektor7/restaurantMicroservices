package com.example.restaurant;

import com.example.restaurant.domain.Booking;
import com.example.restaurant.domain.controller.BookingController;
import com.example.restaurant.domain.repository.BookingRepository;
import com.example.restaurant.domain.service.BookingService;
import com.example.restaurant.domain.service.impl.BookingServiceImpl;
import com.google.common.collect.Iterables;
import org.junit.Before;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hector on 22/07/16.
 */
public class BookingControllerTests extends AbstractBookingControllerTests {

    protected static final Booking bookingStaticInstance =
            new Booking(BOOKING, BOOKING_NAME, BOOKING_RESTAURANT_ID,
                    BOOKING_USER_ID, LocalDate.now(), LocalTime.now(), BOOKING_TABLE_ID);
    protected TestBookingRepository testUserRepository = new TestBookingRepository();
    protected BookingService userService = new BookingServiceImpl(testUserRepository);

    @Before
    public void setup() {
        bookingController = new BookingController(userService);

    }

    protected static class TestBookingRepository implements BookingRepository {

        private Map<Long, Booking> entities;

        public TestBookingRepository() {
            entities = new HashMap();
            Booking booking = new Booking(1L, BOOKING_NAME, 1L, 1L,
                    LocalDate.now(), LocalTime.now(), 1L);
            entities.put(1L, booking);
            booking = new Booking(2L, "Test booking 2", 2L, 2L,
                    LocalDate.now(), LocalTime.now(), 2L);
            entities.put(2L, booking);
        }

        @Override
        public List<Booking> findByName(String name) {
            List<Booking> bookings = new ArrayList();
            int noOfChars = name.length();
            entities.forEach((k, v) -> {
                if (v.getName().toLowerCase().contains(name.subSequence(0, noOfChars))) {
                    bookings.add(v);
                }
            });
            return bookings;
        }

        @Override
        public Booking save(Booking entity) {
            entities.put(entity.getId(), entity);
            return entities.get(entity.getId());
        }

        @Override
        public <S extends Booking> Iterable<S> save(Iterable<S> entities) {

            Iterables.addAll(this.entities.values(), entities);
            return (Iterable<S>) this.entities;
        }

        @Override
        public Booking findOne(Long id) {
            return entities.get(id);
        }

        @Override
        public boolean exists(Long id) {
            return this.findOne(id) != null;
        }

        @Override
        public Iterable<Booking> findAll() {
            return this.entities.values();
        }

        @Override
        public Iterable<Booking> findAll(Iterable<Long> longs) {
            List<Booking> bookings = new ArrayList<>();

            this.entities.entrySet().stream()
                    .filter(k -> Iterables.contains(longs, k.getKey()))
                    .forEach(k -> bookings.add(k.getValue()));

            return bookings;
        }

        @Override
        public long count() {
            return this.entities.entrySet().stream().count();
        }

        @Override
        public void delete(Long id) {
            this.entities.remove(id);
        }

        @Override
        public void delete(Booking entity) {
            this.entities.remove(entity.getId());
        }

        @Override
        public void delete(Iterable<? extends Booking> entities) {
            entities.forEach(r -> this.entities.remove(r.getId()));
        }

        @Override
        public void deleteAll() {
            this.entities.clear();
        }
    }
}