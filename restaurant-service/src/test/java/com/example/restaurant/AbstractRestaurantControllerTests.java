package com.example.restaurant;

import com.example.restaurant.domain.Restaurant;
import com.example.restaurant.domain.controller.RestaurantController;
import com.example.restaurant.domain.valueobject.RestaurantVO;
import com.google.common.collect.Iterables;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.logging.Logger;

/**
 * Created by hector on 19/07/16.
 */
public abstract class AbstractRestaurantControllerTests {

    protected static final Long RESTAURANT = 1L;
    protected static final String RESTAURANT_NAME = "Big-O Restaurant";

    @Autowired
    RestaurantController restaurantController;

    @Test
    public void validResturantById() {
        Logger.getGlobal().info("Start validResturantById test");
        ResponseEntity<Restaurant> restaurant = restaurantController.findById(RESTAURANT.toString());

        Assert.assertEquals(HttpStatus.OK, restaurant.getStatusCode());
        Assert.assertTrue(restaurant.hasBody());
        Assert.assertNotNull(restaurant.getBody());
        Assert.assertEquals(RESTAURANT, restaurant.getBody().getId());
        Assert.assertEquals(RESTAURANT_NAME, restaurant.getBody().getName());
        Logger.getGlobal().info("End validResturantById test");
    }

    @Test
    public void validResturantByName() {
        Logger.getGlobal().info("Start validResturantByName test");
        ResponseEntity<Iterable<Restaurant>> restaurants = restaurantController.findByName(RESTAURANT_NAME);
        Logger.getGlobal().info("In validAccount test");

        Assert.assertEquals(HttpStatus.OK, restaurants.getStatusCode());
        Assert.assertTrue(restaurants.hasBody());
        Assert.assertNotNull(restaurants.getBody());
        Assert.assertFalse(Iterables.isEmpty(restaurants.getBody()));
        Restaurant restaurant = restaurants.getBody().iterator().next();
        Assert.assertEquals(RESTAURANT, restaurant.getId());
        Assert.assertEquals(RESTAURANT_NAME, restaurant.getName());
        Logger.getGlobal().info("End validResturantByName test");
    }

    @Test
    public void validAdd() {
        Logger.getGlobal().info("Start validAdd test");
        RestaurantVO restaurant = new RestaurantVO();
        restaurant.setId("999");
        restaurant.setName("Test Restaurant");

        ResponseEntity<Restaurant> restaurants = restaurantController.add(restaurant);
        Assert.assertEquals(HttpStatus.CREATED, restaurants.getStatusCode());
        Logger.getGlobal().info("End validAdd test");
    }
}
