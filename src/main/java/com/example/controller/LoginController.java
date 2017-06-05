package com.example.controller;

import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Tomek on 13.05.2017.
 */

@Controller
@RequestMapping("/login")
class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="", method = RequestMethod.GET)
    public String displayRequestPage(Model model) {
        return "logowanie";
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public ModelAndView logging(@RequestParam("username") String username, @RequestParam("password") String password,
                                HttpServletRequest request, HttpServletResponse response) {
//        String pass = "secret-pass";
        if(userService.exists(username,password)) {
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("password", password);
            return new ModelAndView("redirect:/admin");
        } else
            return new ModelAndView("logowanie");
    }

}
