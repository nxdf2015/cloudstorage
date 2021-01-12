package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.beans.Beans;
import java.util.Observable;

@Service
public class UserAuthService  {


    @Autowired
    UserService userService;

    public int getUserid(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user  = userService.findByUserName(authentication.getName());
        return user.getUserid();
    }


}
