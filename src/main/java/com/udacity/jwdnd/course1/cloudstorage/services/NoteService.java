package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.note.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.note.NoteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NoteService {

    @Autowired
    UserAuthService userAuthService;

    @Autowired
    NoteMapper mapper;

    private List<Note> notes;


    public  int  create(NoteForm noteForm,int userid) {
        int id  = mapper.create(Note.from(noteForm,userid));
        updateNotes();
        return id;
    }

    private void updateNotes() {
        int userid = userAuthService.getUserid();
        notes = mapper.getAll(userid);
    }

    public List<Note> getAll() {
        if(notes==null){
            updateNotes();
        }
        return notes;
    }

    public int  edit(NoteForm noteForm,int userid) {
        int count = mapper.update(Note.from(noteForm,userid));
        updateNotes();
        return count;
    }

    public int delete(int noteid) {
      int count =  mapper.deleteOne(noteid);
      updateNotes();
      return count;
    }
}
