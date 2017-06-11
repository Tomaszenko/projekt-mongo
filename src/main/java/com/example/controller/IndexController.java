package com.example.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import com.example.dto.CommentaryDTO;
import com.example.dto.FullCommentaryDTO;
import com.example.dto.FullEntryDTO;
import com.example.models.Commentary;
import com.example.models.Entry;
import com.example.services.EntryService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import javax.validation.Valid;
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
        ArrayList<FullEntryDTO> entries = new ArrayList<>();
        for(Entry entry: entryService.findEntries()) {
            FullEntryDTO entryDTO = new FullEntryDTO();
            entryDTO.setId(entry.getId());
            entryDTO.setTitle(entry.getTitle());
            entryDTO.setIntro(entry.getText().substring(0,Math.min(entry.getText().length(),120))+"...");
            entryDTO.setText(entry.getText());
            entryDTO.setDateTime(entry.getDateTime().toString(pattern));
            entryDTO.setCategory(entry.getCategory());
            System.out.println(entryDTO.getDateTime());

            ArrayList<FullCommentaryDTO> displayableComments = new ArrayList<>();
            for(Commentary commentary: entry.getCommentaries()) {
                FullCommentaryDTO newDTO = new FullCommentaryDTO();
                newDTO.setText(commentary.getText());
                newDTO.setNick(commentary.getNick());
                newDTO.setDateTime(commentary.getDateTime().toString(pattern));
                System.out.println(newDTO.getDateTime());
                displayableComments.add(newDTO);
            }
            entryDTO.setCommentaries(displayableComments);
            entries.add(entryDTO);
        }

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("entries", entries);
        mav.addObject("categories", entryService.findCategories());
//        mav.addObject("newComment", new CommentaryDTO());

//        String publText = "Opublikowano "+18 maja 2017 | Książki

        return mav;
    }

    @RequestMapping("kategoria/{nazwa}")
    public ModelAndView findEntriesForCategory(@PathVariable("nazwa") String cat) {
        System.out.println(cat);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        ArrayList<Entry> fromDB = entryService.findEntriesToCategory(cat);
        ArrayList<FullEntryDTO> entries = new ArrayList<>();
        for(Entry entry: fromDB) {
            FullEntryDTO entryDTO = new FullEntryDTO();
            System.out.println(entryDTO.getId());
            System.out.println(entryDTO.getTitle());
            System.out.println(entryDTO.getText());
            entryDTO.setId(entry.getId());
            entryDTO.setTitle(entry.getTitle());
            entryDTO.setIntro(entry.getText().substring(0,Math.min(entry.getText().length(),120))+"...");
            entryDTO.setText(entry.getText());
            entryDTO.setCategory(entry.getCategory());

            ArrayList<FullCommentaryDTO> displayableComments = new ArrayList<>();
            for(Commentary commentary: entry.getCommentaries()) {
                FullCommentaryDTO newDTO = new FullCommentaryDTO();
                newDTO.setText(commentary.getText());
                newDTO.setNick(commentary.getNick());
                newDTO.setDateTime(commentary.getDateTime().toString(pattern));
                System.out.println(newDTO.getText()+"\n"+newDTO.getNick()+"\n"+newDTO.getDateTime());
                displayableComments.add(newDTO);
            }
            entryDTO.setCommentaries(displayableComments);
            entries.add(entryDTO);
        }

        mav.addObject("entries", entries);
        mav.addObject("categories", entryService.findCategories());
//        mav.addObject("newComment", new CommentaryDTO());

        return mav;
    }

    @RequestMapping("komentarz/{id_wpisu}")
    public ModelAndView addComment(@PathVariable("id_wpisu") String entryId,
                                   @ModelAttribute("komentarz") @Valid CommentaryDTO dto) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        Commentary commentary = new Commentary();
        commentary.setText(dto.getText());
        commentary.setNick(dto.getNick());
        commentary.setDateTime(new DateTime());
        entryService.addCommentToEntry(commentary, entryId);

        return new ModelAndView("redirect:/");
    }


}
