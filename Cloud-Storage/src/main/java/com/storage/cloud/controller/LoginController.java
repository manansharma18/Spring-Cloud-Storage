package com.storage.cloud.controller;

import com.storage.cloud.model.User;
import com.storage.cloud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getLogin(){
        return "login";
    }


    @PostMapping
    public String userLogin(@ModelAttribute("user") User user, Model model){
        String loginMessage = null;
        User loggedInUser = userService.getUser(user.getUsername());
        if (loggedInUser!=null){
            loginMessage = "Login successful";
            model.addAttribute("loginMessage",loginMessage);
        }
        else{
            loginMessage = "Invalid username or password";
            model.addAttribute("loginMessage",loginMessage);
        }
        return "login";
    }


}
