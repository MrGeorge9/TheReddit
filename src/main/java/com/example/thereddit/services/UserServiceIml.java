package com.example.thereddit.services;

import com.example.thereddit.models.User;
import com.example.thereddit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserServiceIml implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceIml(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser (User user){
        userRepository.save(user);
    }

    public String validateUserAndRedirect (Model model,String name, String password){
        if  (userRepository.findByNameEqualsAndPasswordEquals(name,password)!=null){
            return "redirect:/mainPage/"+name;
        } else {
            model.addAttribute("error", 0);
            return "login";
        }
    }

    public String validateRegistrationAndRedirect (Model model, String name, String password, String passwordAgain){
        if (userRepository.findByName(name)==null && password.equals(passwordAgain)){
            userRepository.save(new User(name,password));
            return "redirect:/login";
        } else {
            model.addAttribute("error", 0);
            return "register";
        }
    }
}
