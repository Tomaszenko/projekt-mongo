package com.example.repositories;

import com.example.models.Commentary;
import com.example.models.Entry;
import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * Created by Tomek on 05.06.2017.
 */
public interface MyCustomEntryRepository {
    void insertEntry(Entry entry);
    void editEntry(String entryId, Entry entry);
    ArrayList<Commentary> getCommentariesToEntry(String entryId);
    DateTime getDateTimeToEntry(String entryId);
    ArrayList<Entry> findEntriesFromNewest();
}
