package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.note.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
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

//     todo update userid with authentication line 23 29

     @PostMapping
     public String createNote(NoteForm noteform,Model model) {
         noteService.create(noteform,1);
         model.addAttribute("success",true);
         return "result";
     }

     @PostMapping("/edit/{id}")
     public String editNote(NoteForm noteForm, @PathVariable("id") int noteid, Model model){
         noteForm.setNoteid(noteid);
         noteService.edit(noteForm, 1);
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
