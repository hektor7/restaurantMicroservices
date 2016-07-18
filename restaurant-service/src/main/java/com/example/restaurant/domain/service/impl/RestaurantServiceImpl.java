package com.example.restaurant.domain.service.impl;

import com.example.restaurant.domain.Restaurant;
import com.example.restaurant.domain.repository.RestaurantRepository;
import com.example.restaurant.domain.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by hector on 13/07/16.
 */
@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public Iterable<Restaurant> getAllRestaurants() {
        return this.restaurantRepository.findAll();
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return this.restaurantRepository.findOne(id);
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return this.restaurantRepository.save(restaurant);
    }

    @Override
    public void removeRestaurant(Restaurant restaurant) {
        this.restaurantRepository.delete(restaurant);
    }

    @Override
    public Iterable<Restaurant> findByName(String name) {
        return this.restaurantRepository.findByName(name);
    }

    @Override
    public Iterable<Restaurant> findByCriteria(Map<String, List<String>> name){
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
