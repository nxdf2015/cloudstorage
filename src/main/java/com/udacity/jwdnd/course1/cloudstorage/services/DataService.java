package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataService {

    @Autowired
    UserService userService;

    @Autowired
    NoteService noteService;

    @Autowired
    CredentialService credentialService;

    public void clear() {

        noteService.clear();
        credentialService.clear();
        userService.clear();
    }
}
