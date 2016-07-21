package com.example.restaurant.domain.controller;

import com.example.restaurant.domain.User;
import com.example.restaurant.domain.service.UserService;
import com.example.restaurant.domain.valueobject.UserVO;
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
 * Created by hector on 21/07/16.
 */
@RestController
@RequestMapping("/v1/users")
public class UserController {

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    protected UserService userService;

    public UserController() {
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Fetch restaurants with the specified name. A partial case-insensitive
     * match is supported. So <code>http://.../restaurants/rest</code> will find
     * any restaurants with upper or lower case 'rest' in their name.
     *
     * @param name
     * @return A non-null, non-empty collection of restaurants.
     */
    public ResponseEntity<Iterable<User>> findByName(@RequestParam("name") String name) {
        logger.info(String.format("user-service findByName() invoked:{} for {} ",
                userService.getClass().getName(), name));

        name = name.trim().toLowerCase();
        Iterable<User> users;
        try {
            users = userService.findByName(name);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised findByName REST Call", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return !Iterables.isEmpty(users) ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Fetch restaurants with the given id.
     * <code>http://.../v1/restaurants/{restaurant_id}</code> will return
     * restaurant with given id.
     *
     * @param id
     * @return A non-null, non-empty collection of restaurants.
     */
    @RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
    public ResponseEntity<User> findById(@PathVariable("user_id") String id) {

        logger.info(String.format("user-service findById() invoked:{} for {} ", userService.getClass().getName(), id));
        Long userId = 0L;

        if (StringUtils.isNumeric(id)) {
            userId = Long.valueOf(id);
        }
        User user;
        try {
            user = userService.getUserById(userId);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception raised findById REST Call", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return user != null ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Add restaurant with the specified information.
     *
     * @param userVO
     * @return A non-null restaurant.
     * @throws RestaurantNotFoundException If there are no matches at all.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> add(@RequestBody UserVO userVO) {

        logger.info(String.format("user-service add() invoked: %s for %s", userService.getClass().getName(), userVO.getName()));

        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        try {
            this.userService.saveUser(user);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised add User REST Call " + ex);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

