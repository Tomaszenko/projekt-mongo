package com.example.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import com.example.config.MongoConfiguration;
import com.example.models.User;
import com.example.repositories.UserRepository;
import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
class IndexController {
    @Autowired
    private UserRepository repo;
//	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value="/{name:[A-z]+}/**", method=RequestMethod.GET)
    public String displayRequestPage(@PathVariable("name") String userName, Model model) {
//        logger.debug("made it to controller");
        User user = new User();
        user.setName(userName);
        repo.insert(user);
//        model.addAttribute("user", userName);
        return "index";
    }
}
