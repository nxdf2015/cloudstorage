package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.note.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert("insert into notes (notetitle,notedescription,userid)" +
            "values(#{notetitle},#{notedescription},#{userid})")
    @Options(keyProperty = "noteid" , useGeneratedKeys = true)
    public int create(Note note);

    @Select("select noteid, notetitle, notedescription, userid from notes where userid = #{userid}")
    public List<Note> getAll(int userid);

    @Update("update notes set notetitle=#{notetitle}, notedescription=#{notedescription} where noteid=#{noteid}")
    int update(Note from);

    @Delete("delete from notes where noteid=#{noteid}")
    int deleteOne(int noteid);

    @Delete("delete from notes")
    void clear();
}
