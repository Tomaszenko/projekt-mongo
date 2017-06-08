package com.example.controller;

import com.example.dto.NewEntryDTO;
import com.example.models.Entry;
import com.example.services.EntryService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Tomek on 07.06.2017.
 */

@RestController
@RequestMapping("/rest")
public class AjaxController {

    @Autowired
    private EntryService entryService;

    @RequestMapping(value="/entry/{id}", method = RequestMethod.GET, produces = "application/json")
    public Entry getEntryInJSON(@PathVariable String id) {
        Entry entry = entryService.findEntry(id);
//        System.out.println(search.getUsername());
//        System.out.println(search.getEmail());
//        ResponseObject obj = new ResponseObject();
//        obj.setUsermail(search.getUsername().substring(0,3) + search.getEmail());
        return entry;
    }


    @RequestMapping(value="/entries", method = RequestMethod.GET)
    public ArrayList<Entry> getEntriesInJSON() {
        ArrayList<Entry> entries = entryService.findEntries();
        return entries;
    }

//    @RequestMapping(value="/new-entry", method = RequestMethod.POST)
//    public ArrayList<Entry> addEntryViaJSON(@RequestBody NewEntryDTO dto) {
//        entryService.insertEntry();
//        ArrayList<Entry> entries = entryService.findEntries();
//        return entries;
//    }

    @RequestMapping(value="/new", method = RequestMethod.POST)
    public void addEntry(HttpServletRequest request, @RequestBody NewEntryDTO dto) {
        if(request.getSession(false)==null) {
//            return new ModelAndView("redirect:/login");
        }
        else {
            System.out.println("kukulele");
            Entry entry = new Entry();
            entry.setTitle(dto.getTitle());
            entry.setText(dto.getText());
            entry.setCategory(dto.getCategory());
            entry.setDateTime(new DateTime());
            entry.setCommentaries(new ArrayList<>());
            entryService.insertEntry(entry);
            System.out.println("kukulele");
        }
    }
}
