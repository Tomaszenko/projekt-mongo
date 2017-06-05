package com.example.services;

import com.example.models.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Tomek on 24.05.2017.
 */

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    @Override
    public boolean exists(String username, String password) {
        return userRepository.existsByUsernameAndPassword(username,password);
    }

    @Override
    public User findUser(String username) {
        return userRepository.findUserByUsername(username);
    }
}
