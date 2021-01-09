package com.udacity.jwdnd.course1.cloudstorage.model.note;

public class Note {
    private int userid,noteid;
    private String notetitle,notedescription;

    public Note(int noteid, String notetitle, String notedescription,int userid) {
        this.userid = userid;
        this.noteid = noteid;
        this.notetitle = notetitle;
        this.notedescription = notedescription;
    }

    public static Note from(NoteForm noteForm, int userid) {
       return  new Note(noteForm.getNoteid(),
                noteForm.getNotetitle(),
                noteForm.getNotedescription(),
                userid);
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getNoteid() {
        return noteid;
    }

    public void setNoteid(int noteid) {
        this.noteid = noteid;
    }

    public String getNotetitle() {
        return notetitle;
    }

    public void setNotetitle(String notetitle) {
        this.notetitle = notetitle;
    }

    public String getNotedescription() {
        return notedescription;
    }

    public void setNotedescription(String notedescription) {
        this.notedescription = notedescription;
    }

    @Override
    public String toString() {
        return "Note{" +
                "userid=" + userid +
                ", noteid=" + noteid +
                ", notetitle='" + notetitle + '\'' +
                ", notedescription='" + notedescription + '\'' +
                '}';
    }
}
