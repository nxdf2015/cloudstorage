package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.user.User;
import com.udacity.jwdnd.course1.cloudstorage.model.user.UserForm;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("error",true);
        return "login";
    }

    @PostMapping("/logout")
    public String logout(){
        return "redirect:/logout";
    }

    @GetMapping("/signup")
    public String signup(@ModelAttribute("user") User user){
        return "signup";
    }

    @PostMapping("/signup")
    public String createAccount(User user, Model model, RedirectAttributes redirectAttributes){

        if(userService.save(user)){
            redirectAttributes.addFlashAttribute("signup",true);
            return "redirect:/login";
        }
        else {
            model.addAttribute("user", new User());
            model.addAttribute("error","user already exist");
        }
        return "signup";
    }






}
