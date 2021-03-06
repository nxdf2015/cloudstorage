package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.note.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.note.NoteForm;
import org.apache.ibatis.annotations.Delete;
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


    public  int  create(NoteForm noteForm) {
        int userid = userAuthService.getUserid();
        int id  = mapper.create(Note.from(noteForm,userid));
        return id;
    }


    public List<Note> getAll() {
        int userid = userAuthService.getUserid();
        return mapper.getAll(userid);
    }

    public int  edit(NoteForm noteForm) {
        int userid = userAuthService.getUserid();
        int count = mapper.update(Note.from(noteForm,userid));
        return count;
    }

    public int delete(int noteid) {
      int count =  mapper.deleteOne(noteid);
      return count;
    }


    public void clear() {
        mapper.clear();
    }
}
