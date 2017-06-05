package com.example.services;

import com.example.models.Entry;

import java.util.ArrayList;

/**
 * Created by Tomek on 05.06.2017.
 */
public interface EntryService {
    void insertEntry(Entry entry);
    ArrayList<Entry> findEntries();
    void deleteEntry(String entryId);
}
