package com.example.thereddit.services;

import com.example.thereddit.models.User;
import org.springframework.ui.Model;

public interface UserService {
    void saveUser (User user);
    String validateUserAndRedirect (Model model, String name, String password);
    String validateRegistrationAndRedirect (Model model, String name, String password, String passwordAgain);
}
