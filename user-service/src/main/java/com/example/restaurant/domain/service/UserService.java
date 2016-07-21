package com.example.restaurant.domain.service;

import com.example.restaurant.domain.User;

import java.util.List;
import java.util.Map;

/**
 * Created by hector on 13/07/16.
 */
public interface UserService {

    Iterable<User> getAllUsers();

    User getUserById(Long id);

    User saveUser(User user);

    void removeUser(User user);

    Iterable<User> findByName(String name);

    Iterable<User> findByCriteria(Map<String, List<String>> name);

}
