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
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUser(){
        return "signup";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user")User user,Model model){

        String errorMessage = null;
        if(userService.isUserNameAvailable(user.getUsername())){

            int id = userService.addUser(user);
            if(!(id>0)){
                errorMessage = "Error in adding the user. Try again with a different user!";
            }

        }
        else{
            errorMessage ="Username already taken. Please try other name.";
        }

        if (errorMessage == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", errorMessage);
        }
        return "signup";
    }


}
