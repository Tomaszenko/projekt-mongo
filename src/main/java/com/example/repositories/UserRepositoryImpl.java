package com.example.repositories;

import com.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * Created by Tomek on 13.05.2017.
 */

public class UserRepositoryImpl implements MyCustomUserRepository {

    private final MongoOperations operations;

    @Autowired
    public UserRepositoryImpl(MongoOperations operations) {
        Assert.notNull(operations, "MongoOperations must not be null!");
        this.operations = operations;
    }

    @Override
    public boolean existsByUsernameAndPassword(String username, String password) {
        Query query=new Query();
        query.addCriteria(Criteria.where("username").is(username).andOperator(Criteria.where("password").is(password)));
        return operations.exists(query, User.class);
    }

    @Override
    public User findUserByUsername(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        return operations.findOne(query, User.class);
    }

}
