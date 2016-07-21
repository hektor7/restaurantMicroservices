package com.example.restaurant;

import com.example.restaurant.domain.User;
import com.example.restaurant.domain.controller.UserController;
import com.example.restaurant.domain.service.UserService;
import com.example.restaurant.domain.service.impl.UserServiceImpl;
import com.google.common.collect.Iterables;
import org.junit.Before;

import com.example.restaurant.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hector on 21/07/16.
 */
public class UserControllerTests extends AbstractUserControllerTests {

    protected static final User userStaticInstance = new User(USER,
            USER_NAME);

    protected static class TestUserRepository implements UserRepository {

        private Map<Long, User> entities;

        public TestUserRepository() {
            entities = new HashMap();
            User user = new User(1L, "John");
            entities.put(1L, user);
            user = new User(2L, "Hector");
            entities.put(2L, user);
        }

        @Override
        public List<User> findByName(String name) {
            List<User> users = new ArrayList();
            int noOfChars = name.length();
            entities.forEach((k, v) -> {
                if (v.getName().toLowerCase().contains(name.subSequence(0, noOfChars))) {
                    users.add(v);
                }
            });
            return users;
        }

        @Override
        public User save(User entity) {
            entities.put(entity.getId(), entity);
            return entities.get(entity.getId());
        }

        @Override
        public <S extends User> Iterable<S> save(Iterable<S> entities) {

            Iterables.addAll(this.entities.values(), entities);
            return (Iterable<S>) this.entities;
        }

        @Override
        public User findOne(Long id) {
            return entities.get(id);
        }

        @Override
        public boolean exists(Long id) {
            return this.findOne(id) != null;
        }

        @Override
        public Iterable<User> findAll() {
            return this.entities.values();
        }

        @Override
        public Iterable<User> findAll(Iterable<Long> longs) {
            List<User> users = new ArrayList<>();

            this.entities.entrySet().stream()
                    .filter(k -> Iterables.contains(longs, k.getKey()))
                    .forEach(k -> users.add(k.getValue()));

            return users;
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
        public void delete(User entity) {
            this.entities.remove(entity.getId());
        }

        @Override
        public void delete(Iterable<? extends User> entities) {
            entities.forEach(r -> this.entities.remove(r.getId()));
        }

        @Override
        public void deleteAll() {
            this.entities.clear();
        }
    }

    protected TestUserRepository testUserRepository = new TestUserRepository();
    protected UserService userService = new UserServiceImpl(testUserRepository);

    @Before
    public void setup() {
        userController = new UserController(userService);

    }
}