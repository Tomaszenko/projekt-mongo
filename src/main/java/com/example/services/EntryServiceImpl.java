package com.example.services;

import com.example.models.Entry;
import com.example.repositories.EntryRepository;
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

    public void insertEntry(Entry entry) {
        entryRepository.insertEntry(entry);
    }

    public ArrayList<Entry> findEntries() {
        return new ArrayList<>(entryRepository.findAll());
    }

    public void deleteEntry(String entryId) {
        entryRepository.delete(entryId);
    }
}
