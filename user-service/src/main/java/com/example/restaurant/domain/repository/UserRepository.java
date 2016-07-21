package com.example.restaurant.domain.repository;
import com.example.restaurant.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hector on 21/07/16.
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    List<User> findByName(String name);
}