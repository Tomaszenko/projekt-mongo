package com.example.controller;

import com.example.dto.NewEntryDTO;
import com.example.dto.ShowEntryDTO;
import com.example.dto.UserDTO;
import com.example.models.Entry;
import com.example.models.User;
import com.example.services.EntryService;
import com.example.services.FileService;
import com.example.services.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TimeZone;

/**
 * Created by Tomek on 03.06.2017.
 */

@Controller
@RequestMapping("/admin")
@PropertySource( value={"classpath:hobbies.properties"})
public class AdminController {

    private static final String pattern = "dd-MM-yyyy HH:mm";

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
            ArrayList<ShowEntryDTO> entries = new ArrayList<>();
            for(Entry entry: entryService.findEntries()) {
                ShowEntryDTO entryDTO = new ShowEntryDTO();
                entryDTO.setId(entry.getId());
                entryDTO.setTitle(entry.getTitle());
                entryDTO.setShorterText(entry.getText().substring(0,Math.min(entry.getText().length(), 120))+"...");
                entryDTO.setText(entry.getText());
                entryDTO.setCommentaries(entry.getCommentaries());
                entryDTO.setDateTime(entry.getDateTime().toString(pattern));
                entryDTO.setTags(entry.getTags());
                entries.add(entryDTO);
            }
            String[] hobbies = new String[]{"dyskusje", "film", "gotowanie", "gry", "historia",
                    "muzyka", "sport", "spotkania", "turystyka", "wolontariat"};
            UserDTO dto = new UserDTO();
            ModelAndView mav = new ModelAndView();
            mav.setViewName("admin");
            mav.addObject("dto",dto);
            mav.addObject("hobbiesList", hobbies);
            mav.addObject("entries", entries);
            return mav;
        }
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public ModelAndView adminChange(HttpServletRequest request,
                                    @ModelAttribute("admin") @Valid UserDTO adminDTO,
                                    BindingResult bindingResult) {
        if (request.getSession(false) == null)
            return new ModelAndView(("redirect:/login"));
        else {
            if (bindingResult.hasErrors()) {
                System.out.println("nieprawidłowo");
                String[] hobbies = new String[]{"dyskusje", "film", "gotowanie", "gry", "historia",
                        "muzyka", "sport", "spotkania", "turystyka", "wolontariat"};
                ModelAndView mav = new ModelAndView();
                mav.setViewName("admin");
                mav.addObject("dto", adminDTO);
                mav.addObject("hobbiesList", hobbies);

                return mav;
            } else
                return new ModelAndView("redirect:/");
        }
    }

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
            ShowEntryDTO entryDTO = new ShowEntryDTO();
            entryDTO.setId(entry.getId());
            entryDTO.setTitle(entry.getTitle());
            entryDTO.setText(entry.getText());
            entryDTO.setCommentaries(entry.getCommentaries());
            entryDTO.setDateTime(entry.getDateTime().toString(pattern));
            entryDTO.setTags(entry.getTags());

            ArrayList<String> possibleTags = new ArrayList<>();
            possibleTags.add("historia");
            possibleTags.add("polityka");
            possibleTags.add("kulinaria");
            possibleTags.add("sport");

            ModelAndView mav = new ModelAndView();
            mav.setViewName("edit");
            mav.addObject("dto",entryDTO);
            mav.addObject("possibleTags", possibleTags);
            return mav;
        }
    }

    @RequestMapping(value="/edit/{id}", method = RequestMethod.POST)
    public ModelAndView confirmEditEntry(@PathVariable("id") String id, @ModelAttribute("dto") @Valid ShowEntryDTO dto, BindingResult bindingResult, HttpServletRequest request) {
        if(request.getSession(false)==null)
            return new ModelAndView("redirect:/login");
        else {
            if(bindingResult.hasErrors()) {
                ModelAndView toReturn = new ModelAndView();
                System.out.println("NIE PRZESZLO WALIDACJI");
                System.out.println(dto.getDateTime());
                System.out.println(dto.getId());
                System.out.println(dto.getCommentaries());
                toReturn.setViewName("edit");
                toReturn.addObject("dto", dto);
                return toReturn;
            }
            System.out.println("edycja");
            Entry entry = new Entry();
            entry.setTitle(dto.getTitle());
            entry.setText(dto.getText());
            entry.setCommentaries(dto.getCommentaries());
            entry.setTags(dto.getTags());
            DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
            DateTime dt = formatter.parseDateTime(dto.getDateTime());
            entry.setDateTime(dt);

            entryService.editEntry(id, entry);

            return new ModelAndView("redirect:/admin");
        }
    }


    @RequestMapping(value="/new", method = RequestMethod.GET)
    public ModelAndView addEntry(HttpServletRequest request) {
        if(request.getSession(false)==null)
            return new ModelAndView("redirect:/login");
        else {
            NewEntryDTO dto = new NewEntryDTO();
            ArrayList<String> possibleTags = new ArrayList<>();
            possibleTags.add("historia");
            possibleTags.add("polityka");
            possibleTags.add("kulinaria");
            possibleTags.add("sport");
            ModelAndView mav = new ModelAndView();
            mav.setViewName("post");
            mav.addObject("dto", dto);
            mav.addObject("possibleTags", possibleTags);
            return mav;
        }
    }

    @RequestMapping(value="/new", method = RequestMethod.POST)
    public ModelAndView addNewEntry(HttpServletRequest request,
                                    @ModelAttribute("dto") @Valid NewEntryDTO entryDTO,
                                    BindingResult bindingResult) {
        if(request.getSession(false)==null)
            return new ModelAndView("redirect:/login");
        else {
            if(bindingResult.hasErrors())
                return new ModelAndView("post");
            else {
                Entry newEntry = new Entry();
                System.out.println("TITLE="+entryDTO.getTitle());
                System.out.println("TEXT="+entryDTO.getText());
                newEntry.setTitle(entryDTO.getTitle());
                newEntry.setText(entryDTO.getText());
                newEntry.setTags(entryDTO.getTags());
                newEntry.setCommentaries(new ArrayList<>());
                newEntry.setDateTime(new DateTime());
                entryService.insertEntry(newEntry);
                return new ModelAndView("redirect:/admin");
            }
        }
    }
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
