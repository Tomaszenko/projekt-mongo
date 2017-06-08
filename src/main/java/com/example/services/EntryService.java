package com.example.services;

import com.example.models.Commentary;
import com.example.models.Entry;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Tomek on 05.06.2017.
 */
public interface EntryService {
    void insertEntry(Entry entry);
    ArrayList<Entry> findEntries();
    Entry findEntry(String entryId);
    void editEntry(String entryId, Entry entry);
    void deleteEntry(String entryId);
    ArrayList<Commentary> getCommentariesToEntry(String entryId);
    DateTime getDateTimeToEntry(String entryId);
//    ArrayList<Entry> findEntriesByTags(Optional<ArrayList<String>> tags);
//    ArrayList<Entry> findEntriesByDate(Optional<DateTime> startTime, Optional<DateTime> endTime);
}
