package com.example.controller;

import com.example.dto.*;
import com.example.models.Entry;
import com.example.models.User;
import com.example.services.EntryService;
import com.example.services.FileService;
import com.example.services.UserService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Tomek on 03.06.2017.
 */

@Controller
@RequestMapping("/admin")
@PropertySource( value={"classpath:hobbies.properties"})
public class AdminController {

    private static final String pattern = "dd-MM-yyyy HH:mm:ss";

    @Autowired
    private Environment environment;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Autowired
    private EntryService entryService;

    @RequestMapping(value="", method = RequestMethod.GET)
    public ModelAndView adminView(HttpServletRequest request) {
        if(request.getSession(false)==null)
            return new ModelAndView("redirect:/login");
        else {
            ArrayList<IntroEntryDTO> entries = new ArrayList<>();
            for(Entry entry: entryService.findEntries()) {
                IntroEntryDTO entryDTO = new IntroEntryDTO();
                entryDTO.setId(entry.getId());
                entryDTO.setTitle(entry.getTitle());
                entryDTO.setDateTime(entry.getDateTime().toString(pattern));
//                entryDTO.setCategory(entry.getCategory());
                entries.add(entryDTO);
            }
            String[] hobbies = new String[]{"dyskusje", "film", "gotowanie", "gry", "historia",
                    "muzyka", "sport", "spotkania", "turystyka", "wolontariat"};
//            UserDTO dto = new UserDTO();
            ModelAndView mav = new ModelAndView();
            mav.setViewName("admin");
//            mav.addObject("dto",dto);
//            mav.addObject("hobbiesList", hobbies);
            mav.addObject("entries", entries);
            return mav;
        }
    }

//    @RequestMapping(value="", method = RequestMethod.POST)
//    public ModelAndView adminChange(HttpServletRequest request,
//                                    @ModelAttribute("admin") @Valid UserDTO adminDTO,
//                                    BindingResult bindingResult) {
//        if (request.getSession(false) == null)
//            return new ModelAndView(("redirect:/login"));
//        else {
//            if (bindingResult.hasErrors()) {
//                System.out.println("nieprawidłowo");
//                String[] hobbies = new String[]{"dyskusje", "film", "gotowanie", "gry", "historia",
//                        "muzyka", "sport", "spotkania", "turystyka", "wolontariat"};
//                ModelAndView mav = new ModelAndView();
//                mav.setViewName("admin");
//                mav.addObject("dto", adminDTO);
//                mav.addObject("hobbiesList", hobbies);
//
//                return mav;
//            } else
//                return new ModelAndView("redirect:/");
//        }
//    }

    @RequestMapping("/delete/{id}")
    public ModelAndView deleteEntry(@PathVariable("id") String id, HttpServletRequest request) {
        if (request.getSession(false) == null)
            return new ModelAndView("redirect:/login");
        else {
            System.out.println("usuwanie");
            entryService.deleteEntry(id);
            return new ModelAndView("redirect:/admin");
        }
    }

    @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editEntry(@PathVariable("id") String id, HttpServletRequest request) {
        if(request.getSession(false)==null)
            return new ModelAndView("redirect:/login");
        else {
            System.out.println("edycja");
            Entry entry = entryService.findEntry(id);
            EditEntryDTO entryDTO = new EditEntryDTO();
            entryDTO.setId(entry.getId());
            entryDTO.setTitle(entry.getTitle());
            entryDTO.setText(entry.getText());
            entryDTO.setCategory(entry.getCategory());

            ArrayList<String> possibleTags = new ArrayList<>();
            possibleTags.add("sport");
            possibleTags.add("historia");
            possibleTags.add("zdrowie");
            possibleTags.add("turystyka");
            possibleTags.add("rozrywka");
            possibleTags.add("IT");
            possibleTags.add("prawo");
            possibleTags.add("technologie");
            possibleTags.add("motoryzacja");
            possibleTags.add("książki");

            ModelAndView mav = new ModelAndView();
            mav.setViewName("edit");
            mav.addObject("dto",entryDTO);
            mav.addObject("possibleTags", possibleTags);
            return mav;
        }
    }

    @RequestMapping(value="/edit/{id}", method = RequestMethod.POST)
    public ModelAndView confirmEditEntry(@PathVariable("id") String id, @ModelAttribute("dto") @Valid EditEntryDTO dto,
                                         BindingResult bindingResult, HttpServletRequest request) {
        if(request.getSession(false)==null)
            return new ModelAndView("redirect:/login");
        else {
            if(bindingResult.hasErrors()) {
                ModelAndView toReturn = new ModelAndView();
                System.out.println("NIE PRZESZLO WALIDACJI");
//                System.out.println(dto.getDateTime());
                System.out.println(dto.getId());
                System.out.println(dto.getCategory());
                toReturn.setViewName("edit");
                toReturn.addObject("dto", dto);
                return toReturn;
            }
            System.out.println("edycja");
            Entry entry = new Entry();
            entry.setTitle(dto.getTitle());
            entry.setText(dto.getText());
            entry.setCommentaries(entryService.getCommentariesToEntry(dto.getId()));
            entry.setCategory(dto.getCategory());
//            DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
            DateTime dt = new DateTime();
            entry.setDateTime(dt);

            entryService.editEntry(id, entry);

            return new ModelAndView("redirect:/admin");
        }
    }


    @RequestMapping(value="/new", method = RequestMethod.POST)
    public ModelAndView addEntry(@ModelAttribute("dto") @Valid NewEntryDTO dto, HttpServletRequest request) {
        if(request.getSession(false)==null)
            return new ModelAndView("redirect:/login");
        else {
            Entry newEntry = new Entry();
            System.out.println("TITLE="+dto.getTitle());
            System.out.println("TEXT="+dto.getText());
            newEntry.setTitle(dto.getTitle());
            newEntry.setText(dto.getText());
            newEntry.setCategory(dto.getCategory());
            System.out.println("Na razie chodzi wolno");
            newEntry.setCommentaries(new ArrayList<>());
            newEntry.setDateTime(new DateTime());
            entryService.insertEntry(newEntry);
            return new ModelAndView("redirect:/admin");
        }
    }


    @RequestMapping(value="/new", method = RequestMethod.GET)
    public ModelAndView prepareaddEntry(HttpServletRequest request) {
        if(request.getSession(false)==null)
            return new ModelAndView("redirect:/login");
        else {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("post");
            NewEntryDTO newEntry = new NewEntryDTO();
            System.out.println("TITLE="+newEntry.getTitle());
            System.out.println("TEXT="+newEntry.getText());
            mav.addObject("dto", newEntry);

            ArrayList<String> possibleTags = new ArrayList<>();
            possibleTags.add("historia");
            possibleTags.add("IT");
            possibleTags.add("książki");
            possibleTags.add("motoryzacja");
            possibleTags.add("prawo");
            possibleTags.add("rozrywka");
            possibleTags.add("sport");
            possibleTags.add("technologie");
            possibleTags.add("turystyka");
            possibleTags.add("zdrowie");
            possibleTags.add("inne");
            mav.addObject("possibleTags", possibleTags);

//            newEntry.setCommentaries(new ArrayList<>());
//            newEntry.setDateTime(new DateTime());
//            entryService.insertEntry(newEntry);
            return mav;
        }
    }

//    @RequestMapping(value="/new", method = RequestMethod.POST)
//    public ModelAndView addNewEntry(HttpServletRequest request,
//                                    @ModelAttribute("dto") @Valid NewEntryDTO entryDTO,
//                                    BindingResult bindingResult) {
//        if(request.getSession(false)==null)
//            return new ModelAndView("redirect:/login");
//        else {
//            if(bindingResult.hasErrors())
//                return new ModelAndView("post");
//            else {
//                Entry newEntry = new Entry();
//                System.out.println("TITLE="+entryDTO.getTitle());
//                System.out.println("TEXT="+entryDTO.getText());
//                newEntry.setTitle(entryDTO.getTitle());
//                newEntry.setText(entryDTO.getText());
//                newEntry.setCategory(entryDTO.getCategory());
//                newEntry.setCommentaries(new ArrayList<>());
//                newEntry.setDateTime(new DateTime());
//                entryService.insertEntry(newEntry);
//                return new ModelAndView("redirect:/admin");
//            }
//        }
//    }
//    @RequestMapping("/edit")
//    public ModelAndView adminEditView(HttpServletRequest request, Model model) {
//        if(request.getSession(false)==null)
//            return new ModelAndView("redirect:/login");
//        else {
//            String username = (String)request.getSession().getAttribute("username");
//            User user = userService.findUser(username);
//            model.addAttribute("user", user);
//            return new ModelAndView("admin_edycja");
//        }
//    }

//    @RequestMapping("/updateData")
//    public ModelAndView updateData(HttpServletRequest request,
//                                   @ModelAttribute("admin") @Valid UserDTO adminDTO,
//                                   BindingResult bindingResult, RedirectAttributes attributes) {
//        if(request.getSession(false)==null)
//            return new ModelAndView(("redirect:/login"));
//        else {
//            if(bindingResult.hasErrors()) {
//                System.out.println("nieprawidłowo");
//                String[] hobbies = new String[]{"dyskusje", "film", "gotowanie", "gry", "historia",
//                        "muzyka", "sport", "spotkania", "turystyka", "wolontariat"};
//                ModelAndView mav = new ModelAndView();
//                mav.setViewName("admin");
//                mav.addObject("dto",adminDTO);
//                mav.addObject("hobbiesList", hobbies);
//
//                return mav;
//            }
//            else
//                return new ModelAndView("redirect:/");
//        }


//    }

//    @ReqiestMapping("/change")
//    public ModelAndView changeAdminData(HttpServletRequest request, Model model) {
//        if(request.getSession(false)==null)
//            return new ModelAndView(("redirect:/login"));
//        else {
//            String username = (String)request.getSession()
//        }
//    }

    @RequestMapping("/logout")
    public String onExit(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

//    @RequestMapping(value="/entries", method = RequestMethod.POST)
//    public @ResponseBody ArrayList<Entry> getShopInJSON(@RequestBody SearchCriteria search) {
//
//        ArrayList<Entry> entries = entryService.findEntries();
//        System.out.println(search.getUsername());
//        System.out.println(search.getEmail());
//        return entries;
//    }

    /**
     * Upload single file using Spring Controller
     */
//    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//    public void uploadFileHandler(@RequestParam("file") MultipartFile file) {
//
//        if (!file.isEmpty()) {
//            try {
//                byte[] bytes = file.getBytes();
//
//                MultipartFile file = article.getFileData();  //Will return CommonsMultipartFile
//                String filePath = "C:/MyApplication/UploadedImages/" + file.getOriginalFilename(); //Please note that I am going to remove hardcoaded path to get it from resource/property file
//                File dest = new File(filePath);
//                file.transferTo(dest);
//
//                // Creating the directory to store file
//                String rootPath = System.getProperty("catalina.home");
//                File dir = new File(rootPath + File.separator + "tmpFiles");
//                if (!dir.exists())
//                    dir.mkdirs();
//
//                // Create the file on server
//                File serverFile = new File(dir.getAbsolutePath()
//                        + File.separator + name);
//                BufferedOutputStream stream = new BufferedOutputStream(
//                        new FileOutputStream(serverFile));
//                stream.write(bytes);
//                stream.close();
//
////                logger.info("Server File Location="
////                        + serverFile.getAbsolutePath());
//
//                return "You successfully uploaded file=" + name;
//            } catch (Exception e) {
//                return "You failed to upload " + name + " => " + e.getMessage();
//            }
//        } else {
//            return "You failed to upload " + name
//                    + " because the file was empty.";
//        }
//    }

    /**
     * Upload multiple file using Spring Controller
     */
    @RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
    public @ResponseBody
    String uploadMultipleFileHandler(@RequestParam("name") String[] names,
                                     @RequestParam("file") MultipartFile[] files) {

        if (files.length != names.length)
            return "Mandatory information missing";

        String message = "";
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String name = names[i];
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

//                logger.info("Server File Location="
//                        + serverFile.getAbsolutePath());

                message = message + "You successfully uploaded file=" + name;
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        }
        return message;
    }

    public User dtoToUser(UserDTO dto) {
        User user = new User();
        user.setFirst_name(dto.getFirst_name());
        user.setLast_name(dto.getLast_name());
        user.setDescription(dto.getDescription());
        user.setHobbies(dto.getHobbies());
//        File file = fileService.saveFile(dto.getImage());
//        user.setImgPath(file.getAbsolutePath());
        user.setContact(dto.getContact());

        return user;
    }

    public UserDTO userToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setFirst_name(user.getFirst_name());
        dto.setLast_name(user.getLast_name());
        dto.setDescription(user.getDescription());
        dto.setHobbies(user.getHobbies());
        dto.setContact(user.getContact());
//        dto.setImage(null);

        return dto;
    }
}
