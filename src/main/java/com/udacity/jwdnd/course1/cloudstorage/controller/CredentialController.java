package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.credential.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.credential.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.user.User;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    @Autowired
    CredentialService credentialService;

    @Autowired
    UserService userService;


    @PostMapping
    public String create(CredentialForm credentialForm, Model model, Authentication authentication){
        User user = userService.findByUserName(authentication.getName());
        int id = credentialService.create(credentialForm,user.getUserid());
        model.addAttribute("success",true);
        return "result";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int credentialid, Model model){
        credentialService.delete(credentialid);
        model.addAttribute("success",true);
        return "result";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") int credentialid,CredentialForm credentialForm, Model model,Authentication authentication){
        User user = userService.findByUserName(authentication.getName());
        credentialService.update(credentialForm,credentialid,user.getUserid());
        model.addAttribute("success",true);
        return "result";
    }

    @GetMapping("/decode/{id}")
    @ResponseBody
    public String decode(@PathVariable("id") int id){
        Credential credential = credentialService.decodePassword(id);
        String code =  credential.getPassword();

        return code;
    }

}
