package com.example.restaurant.domain.controller;

import com.example.restaurant.domain.Restaurant;
import com.example.restaurant.domain.service.RestaurantService;

import com.example.restaurant.domain.valueobject.RestaurantVO;
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
 * Created by hector on 13/07/16.
 */
@RestController
@RequestMapping("/v1/restaurants")
public class RestaurantController {

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    protected RestaurantService restaurantService;

    public RestaurantController() {
    }

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    /**
     * Fetch restaurants with the specified name. A partial case-insensitive
     * match is supported. So <code>http://.../restaurants/rest</code> will find
     * any restaurants with upper or lower case 'rest' in their name.
     *
     * @param name
     * @return A non-null, non-empty collection of restaurants.
     */
    public ResponseEntity<Iterable<Restaurant>> findByName(@RequestParam("name") String name){
        logger.info(String.format("restaurant-service findByName() invoked:{} for {} ",
                restaurantService.getClass().getName(), name));

        name = name.trim().toLowerCase();
        Iterable<Restaurant> restaurants;
        try {
            restaurants = restaurantService.findByName(name);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised findByName REST Call", ex);
            return new ResponseEntity<Iterable<Restaurant>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return !Iterables.isEmpty(restaurants) ? new ResponseEntity<Iterable<Restaurant>>(restaurants, HttpStatus.OK)
                : new ResponseEntity<Iterable<Restaurant>>(HttpStatus.NO_CONTENT);
    }

    /**
     * Fetch restaurants with the given id.
     * <code>http://.../v1/restaurants/{restaurant_id}</code> will return
     * restaurant with given id.
     *
     * @param id
     * @return A non-null, non-empty collection of restaurants.
     */
    @RequestMapping(value = "/{restaurant_id}", method = RequestMethod.GET)
    public ResponseEntity<Restaurant> findById(@PathVariable("restaurant_id") String id) {

        logger.info(String.format("restaurant-service findById() invoked:{} for {} ", restaurantService.getClass().getName(), id));
        Long restaurantId = 0L;

        if (StringUtils.isNumeric(id)){
            restaurantId = Long.valueOf(id);
        }
        Restaurant restaurant;
        try {
            restaurant = restaurantService.getRestaurantById(restaurantId);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception raised findById REST Call", ex);
            return new ResponseEntity<Restaurant>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return restaurant != null ? new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK)
                : new ResponseEntity<Restaurant>(HttpStatus.NO_CONTENT);
    }

    /**
     * Add restaurant with the specified information.
     *
     * @param restaurantVO
     * @return A non-null restaurant.
     * @throws RestaurantNotFoundException If there are no matches at all.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Restaurant> add(@RequestBody RestaurantVO restaurantVO) {

        logger.info(String.format("restaurant-service add() invoked: %s for %s", restaurantService.getClass().getName(), restaurantVO.getName()));

        Restaurant restaurant = new Restaurant();
        BeanUtils.copyProperties(restaurantVO, restaurant);
        try {
            this.restaurantService.saveRestaurant(restaurant);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised add Restaurant REST Call "+ ex);
            return new ResponseEntity<Restaurant>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<Restaurant>(HttpStatus.CREATED);
    }
}

