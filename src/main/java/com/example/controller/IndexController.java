package com.example.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import com.example.dto.FullEntryDTO;
import com.example.models.Entry;
import com.example.services.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
class IndexController {

    private static final String pattern = "MM-dd-yyyy HH:mm";

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
        ArrayList<FullEntryDTO> entries = new ArrayList<>();
        for(Entry entry: entryService.findEntries()) {
            FullEntryDTO entryDTO = new FullEntryDTO();
            System.out.println(entryDTO.getId());
            System.out.println(entryDTO.getTitle());
            System.out.println(entryDTO.getText());
            entryDTO.setId(entry.getId());
            entryDTO.setTitle(entry.getTitle());
            entryDTO.setIntro(entry.getText().substring(0,Math.min(entry.getText().length(),120))+"...");
            entryDTO.setText(entry.getText());
            entryDTO.setDateTime(entry.getDateTime().toString(pattern));
            entryDTO.setCategory(entry.getCategory());
            entryDTO.setCommentaries(entry.getCommentaries());
            entries.add(entryDTO);
        }

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("entries", entries);

//        String publText = "Opublikowano "+18 maja 2017 | Książki

        return mav;
    }
}
