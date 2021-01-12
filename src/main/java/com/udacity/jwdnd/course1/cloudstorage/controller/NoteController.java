package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.note.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.user.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/note")
public class NoteController {

     @Autowired
     NoteService noteService;


     @PostMapping
     public String createNote(NoteForm noteform, Model model, Authentication authentication) {
         noteService.create(noteform);
         model.addAttribute("success",true);
         return "result";
     }

     @PostMapping("/edit/{id}")
     public String editNote(NoteForm noteForm, @PathVariable("id") int noteid, Model model,Authentication authentication){

         noteForm.setNoteid(noteid);
         noteService.edit(noteForm);
         model.addAttribute("success",true);
         return "result";
     }

     @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable("id") int noteid,Model model){
         noteService.delete(noteid);
         model.addAttribute("success",true);
         return "result";
     }

}
