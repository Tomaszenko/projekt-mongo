package com.example.services;

import com.example.models.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tomek on 24.05.2017.
 */

public interface UserService {
    boolean exists(String username, String password);
    User findUser(String username);
}
