package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataService {

    @Autowired
    UserService userService;

    @Autowired
    NoteService noteService;

    public void clear() {

        noteService.clear();
        userService.clear();
    }
}
