package com.example.restaurant.domain.repository;
import com.example.restaurant.domain.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hector on 21/07/16.
 */
@Repository
public interface BookingRepository extends CrudRepository<Booking,Long> {

    List<Booking> findByName(String name);
}