package com.example.repositories;

import com.example.models.Commentary;
import com.example.models.Entry;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.Assert;

import java.util.ArrayList;

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
        update.set("category", entry.getCategory());
        update.set("dateTime", entry.getDateTime());
        update.set("commentaries", entry.getCommentaries());
        operations.updateFirst(query, update, Entry.class);
    }

    public ArrayList<Commentary> getCommentariesToEntry(String entryId) {
        Query query = new Query();

        query.addCriteria(Criteria.where("id").is(entryId));
        query.with(new Sort(Sort.Direction.DESC, "commentaries['dateTime']"));

        return new ArrayList<>(operations.find(query, Commentary.class));
    }

    public DateTime getDateTimeToEntry(String entryId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(entryId));
        query.fields().include("dateTime");
        return operations.findOne(query, DateTime.class);
    }

    public ArrayList<Entry> findEntriesFromNewest() {
        Query q = new Query().with(new Sort(Sort.Direction.DESC, "dateTime"));
        return new ArrayList<>(operations.find(q, Entry.class));
    }

    public ArrayList<Entry> findEntriesFromNewestToCategory(String category) {
        Query q = new Query();
        q.addCriteria(Criteria.where("category").is(category));
        q.with(new Sort(Sort.Direction.DESC, "dateTime"));
        return new ArrayList<>(operations.find(q, Entry.class));
    }

    public ArrayList<String> findAllCategories() {
        ArrayList<String> categories = new ArrayList<String>(operations.getCollection("entries").distinct("category"));
        return categories;
    }

    public void addCommentToEntry(Commentary commentary, String entryId) {
        Query query= new Query();
        query.addCriteria(Criteria.where("id").is(entryId));
        Update update = new Update();
        update.push("commentaries", commentary);
        operations.updateFirst(query, update, Entry.class);
    }
}
