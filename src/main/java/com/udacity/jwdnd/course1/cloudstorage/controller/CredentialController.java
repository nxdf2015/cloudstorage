package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.credential.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    @Autowired
    CredentialService credentialService;

    @PostMapping
    public String create(CredentialForm credentialForm){
        int id = credentialService.create(credentialForm, 1);
        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int credentialid){
        credentialService.delete(credentialid);
        return "redirect:/home";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") int credentialid,CredentialForm credentialForm){
        credentialService.update(credentialForm,credentialid,1);
        return "redirect:/home";
    }

}
