package com.example.restaurant;

import com.example.restaurant.domain.Restaurant;
import com.example.restaurant.domain.controller.RestaurantController;
import com.example.restaurant.domain.service.RestaurantService;
import com.example.restaurant.domain.service.impl.RestaurantServiceImpl;
import com.google.common.collect.Iterables;
import org.junit.Before;

import com.example.restaurant.domain.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hector on 19/07/16.
 */
public class RestaurantControllerTests extends AbstractRestaurantControllerTests {

    protected static final Restaurant restaurantStaticInstance = new Restaurant(RESTAURANT,
            RESTAURANT_NAME);

    protected static class TestRestaurantRepository implements RestaurantRepository {

        private Map<Long, Restaurant> entities;

        public TestRestaurantRepository() {
            entities = new HashMap();
            Restaurant restaurant = new Restaurant(1L, "Big-O Restaurant");
            entities.put(1L, restaurant);
            restaurant = new Restaurant(2L, "O Restaurant");
            entities.put(2L, restaurant);
        }

        @Override
        public List<Restaurant> findByName(String name) {
            List<Restaurant> restaurants = new ArrayList();
            int noOfChars = name.length();
            entities.forEach((k, v) -> {
                if (v.getName().toLowerCase().contains(name.subSequence(0, noOfChars))) {
                    restaurants.add(v);
                }
            });
            return restaurants;
        }

        @Override
        public Restaurant save(Restaurant entity) {
            entities.put(entity.getId(), entity);
            return entities.get(entity.getId());
        }

        @Override
        public <S extends Restaurant> Iterable<S> save(Iterable<S> entities) {

            Iterables.addAll(this.entities.values(), entities);
            return (Iterable<S>) this.entities;
        }

        @Override
        public Restaurant findOne(Long id) {
            return entities.get(id);
        }

        @Override
        public boolean exists(Long id) {
            return this.findOne(id) != null;
        }

        @Override
        public Iterable<Restaurant> findAll() {
            return this.entities.values();
        }

        @Override
        public Iterable<Restaurant> findAll(Iterable<Long> longs) {
            List<Restaurant> restaurants = new ArrayList<>();

            this.entities.entrySet().stream()
                    .filter(k -> Iterables.contains(longs, k.getKey()))
                    .forEach(k -> restaurants.add(k.getValue()));

            return restaurants;
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
        public void delete(Restaurant entity) {
            this.entities.remove(entity.getId());
        }

        @Override
        public void delete(Iterable<? extends Restaurant> entities) {
            entities.forEach(r -> this.entities.remove(r.getId()));
        }

        @Override
        public void deleteAll() {
            this.entities.clear();
        }
    }

    protected TestRestaurantRepository testRestaurantRepository = new TestRestaurantRepository();
    protected RestaurantService restaurantService = new RestaurantServiceImpl(testRestaurantRepository);

    @Before
    public void setup() {
        restaurantController = new RestaurantController(restaurantService);

    }
}