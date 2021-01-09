package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.credential.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.note.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    NoteService noteService;

    @Autowired
    CredentialService credentialService;


    @GetMapping
    public String home(@ModelAttribute("noteform") NoteForm noteform,
                       @ModelAttribute("credentialform") CredentialForm credentialForm,
                       Model model){

        model.addAttribute("notes",noteService.getAll());
        model.addAttribute("credentials",credentialService.getAll());

        return "home";
    }
}
