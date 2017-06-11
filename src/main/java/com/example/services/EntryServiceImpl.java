package com.example.services;

import com.example.models.Commentary;
import com.example.models.Entry;
import com.example.repositories.EntryRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomek on 05.06.2017.
 */

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    EntryRepository entryRepository;

    public void insertEntry(Entry entry) { entryRepository.insertEntry(entry); }

    public void editEntry(String entryId, Entry entry) { entryRepository.editEntry(entryId, entry); }

    public Entry findEntry(String entryId) {
        return entryRepository.findOne(entryId);
    }

    public ArrayList<Entry> findEntries() {
        return entryRepository.findEntriesFromNewest();
    }

    @Override
    public ArrayList<String> findCategories() {
        return entryRepository.findAllCategories();
    }

    @Override
    public ArrayList<Entry> findEntriesToCategory(String category) {
        return entryRepository.findEntriesFromNewestToCategory(category);
    }

    public void deleteEntry(String entryId) {
        entryRepository.delete(entryId);
    }

    @Override
    public ArrayList<Commentary> getCommentariesToEntry(String entryId) {
        return entryRepository.getCommentariesToEntry(entryId);
    }

    public DateTime getDateTimeToEntry(String entryId) {
        return entryRepository.getDateTimeToEntry(entryId);
    }

    @Override
    public void addCommentToEntry(Commentary commentary, String entryId) {
        entryRepository.addCommentToEntry(commentary, entryId);
    }
}
