package com.example.repositories;

import com.example.models.User;

/**
 * Created by Tomek on 13.05.2017.
 */
public interface MyCustomUserRepository {
    boolean existsByUsernameAndPassword(String username, String password);
    User findUserByUsername(String username);
}
