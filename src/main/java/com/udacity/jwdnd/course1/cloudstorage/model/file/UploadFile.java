package com.udacity.jwdnd.course1.cloudstorage.model.file;

import com.udacity.jwdnd.course1.cloudstorage.services.UploadFileService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class UploadFile {

   private int fileid;
   private String filename,contenttype, filesize;
   private int userid;
   private InputStream filedata;

    public static UploadFile from(MultipartFile data,int userid) throws IOException {
        UploadFile uploadFile = new UploadFile();
        uploadFile.setContenttype(data.getContentType());
        uploadFile.setFilename(data.getOriginalFilename());
        uploadFile.setFiledata(data.getInputStream());
        uploadFile.setFilesize(""+data.getSize());
        uploadFile.setUserid(userid);
        return uploadFile;
    }

    public int getFileid() {
        return fileid;
    }

    public void setFileid(int fileid) {
        this.fileid = fileid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public InputStream getFiledata() {
        return filedata;
    }

    public void setFiledata(InputStream filedata) {
        this.filedata = filedata;
    }


}
