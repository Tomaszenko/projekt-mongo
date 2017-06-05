package com.example.repositories;

import com.example.models.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.util.Assert;

/**
 * Created by Tomek on 05.06.2017.
 */
public class EntryRepositoryImpl implements MyCustomEntryRepository {

    @Autowired
    private final MongoOperations operations;

    @Autowired
    public EntryRepositoryImpl(MongoOperations operations) {
        Assert.notNull(operations, "MongoOperations must not be null!");
        this.operations = operations;
    }

    @Override
    public void insertEntry(Entry entry) {
        operations.insert(entry);
    }
}
