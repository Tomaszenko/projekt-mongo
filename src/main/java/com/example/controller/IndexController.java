package com.example.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import com.example.config.MongoConfiguration;
import com.example.dto.ShowEntryDTO;
import com.example.models.Entry;
import com.example.models.User;
import com.example.repositories.EntryRepository;
import com.example.repositories.UserRepository;
import com.example.services.EntryService;
import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
class IndexController {

    private static final String pattern = "dd-MM-yyyy HH:mm";

//	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

//    @RequestMapping(value="/{name:[A-z]+}/**", method=RequestMethod.GET)
//    public String displayRequestPage(@PathVariable("name") String userName, Model model) {
////        logger.debug("made it to controller");
//        User user = new User();
//        user.setName(userName);
//        repo.insert(user);
////        model.addAttribute("user", userName);
//        return "index";
//    }
    @Autowired
    EntryService entryService;

    @RequestMapping("")
    public ModelAndView displayPage() {
        ArrayList<ShowEntryDTO> entries = new ArrayList<>();
        for(Entry entry: entryService.findEntries()) {
            ShowEntryDTO entryDTO = new ShowEntryDTO();
            System.out.println(entryDTO.getId());
            System.out.println(entryDTO.getTitle());
            System.out.println(entryDTO.getText());
            entryDTO.setId(entry.getId());
            entryDTO.setTitle(entry.getTitle());
            entryDTO.setShorterText(entry.getText().substring(0,Math.min(entry.getText().length(),120))+"...");
            entryDTO.setText(entry.getText());
            entryDTO.setDateTime(entry.getDateTime().toString(pattern));
            entryDTO.setTags(entry.getTags());
            entryDTO.setCommentaries(entry.getCommentaries());
            entries.add(entryDTO);
        }

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("entries", entries);

//        String publText = "Opublikowano "+18 maja 2017 | Książki

        return mav;
    }
}
