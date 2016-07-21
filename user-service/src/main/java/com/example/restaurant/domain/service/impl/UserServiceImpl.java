package com.example.restaurant.domain.service.impl;

import com.example.restaurant.domain.User;
import com.example.restaurant.domain.repository.UserRepository;
import com.example.restaurant.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by hector on 21/07/16.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Iterable<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void removeUser(User user) {
        this.userRepository.delete(user);
    }

    @Override
    public Iterable<User> findByName(String name) {
        return this.userRepository.findByName(name);
    }

    @Override
    public Iterable<User> findByCriteria(Map<String, List<String>> name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
