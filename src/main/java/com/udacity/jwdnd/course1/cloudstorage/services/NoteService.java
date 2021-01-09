package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.note.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.note.NoteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NoteService {

    private List<Note> notes;

    public NoteService() {
        notes = new ArrayList<>();
    }

    @Autowired
    NoteMapper mapper;

    public  int  create(NoteForm noteForm,int userid) {
        int id  = mapper.create(Note.from(noteForm,userid));
        updateNotes();
        return id;
    }

    private void updateNotes() {
        notes = mapper.getAll();
    }

    public List<Note> getAll() {
        return notes;
    }

    public int  edit(NoteForm noteForm,int userid) {
        System.out.println(Note.from(noteForm,userid));
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
