package com.example.restaurant.domain.service;

import com.example.restaurant.domain.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hector on 13/07/16.
 */
public interface RestaurantService {

    Iterable<Restaurant> getAllRestaurants();

    Restaurant getRestaurantById(Long id);

    Restaurant saveRestaurant(Restaurant restaurant);

    void removeRestaurant(Restaurant restaurant);

    Iterable<Restaurant> findByName(String name);

    Iterable<Restaurant> findByCriteria(Map<String, List<String>> name);

}
