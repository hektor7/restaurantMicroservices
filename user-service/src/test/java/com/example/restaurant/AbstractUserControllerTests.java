package com.example.restaurant;

import com.example.restaurant.domain.User;
import com.example.restaurant.domain.controller.UserController;
import com.example.restaurant.domain.valueobject.UserVO;
import com.google.common.collect.Iterables;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.logging.Logger;

/**
 * Created by hector on 21/07/16.
 */
public abstract class AbstractUserControllerTests {

    protected static final Long USER = 1L;
    protected static final String USER_NAME = "John";

    @Autowired
    UserController userController;

    @Test
    public void validUserById() {
        Logger.getGlobal().info("Start validUserById test");
        ResponseEntity<User> user = userController.findById(USER.toString());

        Assert.assertEquals(HttpStatus.OK, user.getStatusCode());
        Assert.assertTrue(user.hasBody());
        Assert.assertNotNull(user.getBody());
        Assert.assertEquals(USER, user.getBody().getId());
        Assert.assertEquals(USER_NAME, user.getBody().getName());
        Logger.getGlobal().info("End validUserById test");
    }

    @Test
    public void validUserByName() {
        Logger.getGlobal().info("Start validUserByName test");
        ResponseEntity<Iterable<User>> users = userController.findByName(USER_NAME);
        Logger.getGlobal().info("In validAccount test");

        Assert.assertEquals(HttpStatus.OK, users.getStatusCode());
        Assert.assertTrue(users.hasBody());
        Assert.assertNotNull(users.getBody());
        Assert.assertFalse(Iterables.isEmpty(users.getBody()));
        User restaurant = users.getBody().iterator().next();
        Assert.assertEquals(USER, restaurant.getId());
        Assert.assertEquals(USER_NAME, restaurant.getName());
        Logger.getGlobal().info("End validUserByName test");
    }

    @Test
    public void validAdd() {
        Logger.getGlobal().info("Start validAdd test");
        UserVO userVO = new UserVO();
        userVO.setId("999");
        userVO.setName("Test Restaurant");

        ResponseEntity<User> restaurants = userController.add(userVO);
        Assert.assertEquals(HttpStatus.CREATED, restaurants.getStatusCode());
        Logger.getGlobal().info("End validAdd test");
    }
}
