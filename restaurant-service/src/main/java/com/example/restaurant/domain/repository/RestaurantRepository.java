package com.example.restaurant.domain.repository;
import com.example.restaurant.domain.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hector on 6/07/16.
 */
@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant,Long> {

    List<Restaurant> findByName(String name);
}