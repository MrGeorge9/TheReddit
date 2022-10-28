package com.example.thereddit.controllers;

import com.example.thereddit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String redirectToLogin (){
        return "login";
    }

    @GetMapping("/login")
    public String login (){
        return "login";
    }

    @PostMapping("/login")
    public String login (Model model,
                         @RequestParam(name = "name") String name,
                         @RequestParam(name = "password") String password){
        return userService.validateUserAndRedirect(model,name, password);
    }

    @GetMapping("/register")
    public String register (){
        return "register";
    }

    @PostMapping("/register")
    public String register (Model model,
                            @RequestParam(name = "name") String name,
                            @RequestParam(name = "password") String password,
                            @RequestParam(name = "passwordAgain") String passwordAgain){
        return userService.validateRegistrationAndRedirect(model,name,password,passwordAgain);
    }
}
