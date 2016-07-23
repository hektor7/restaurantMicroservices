package com.example.restaurant;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by hector on 19/07/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestaurantApplication.class)
@WebIntegrationTest
public class RestaurantControllerIntegrationTests extends
        RestaurantControllerTests {

}