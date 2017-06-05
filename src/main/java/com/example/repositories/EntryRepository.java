package com.example.repositories;

import com.example.models.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tomek on 05.06.2017.
 */

@Repository
public interface EntryRepository extends MongoRepository<Entry,String>, MyCustomEntryRepository {

}
