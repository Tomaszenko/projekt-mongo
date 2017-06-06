package com.example.repositories;

import com.example.models.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

    public void editEntry(String entryId, Entry entry) {
        Query query= new Query();
        query.addCriteria(Criteria.where("id").is(entryId));
        Update update = new Update();
        update.set("title", entry.getTitle());
        update.set("text", entry.getText());
        update.set("tags", entry.getTags());
        update.set("dateTime", entry.getDateTime());
        update.set("commentaries", entry.getCommentaries());
        operations.updateFirst(query, update, Entry.class);
    }
}
