package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.exception.InvalidData;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.file.FileData;
import com.udacity.jwdnd.course1.cloudstorage.model.file.UploadFile;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UploadFileService {
    private List<FileData> files;

    @Autowired
    FileMapper fileMapper;

    public int  save(MultipartFile uploadfile,int userid) throws IOException, InvalidData {
        UploadFile uploadFile = UploadFile.from(uploadfile, userid);
        if(fileMapper.findByName(uploadFile.getFilename()) != null){
            throw new InvalidData("file with same name already exist");
        }

        int id = fileMapper.save(uploadFile);
        updateFiles();
        return id;
    }

    private void updateFiles() {
        files = fileMapper.getAll();
    }

    public List<FileData> getAll(){
        return files;
    }

    public int delete(int id) {

        int count = fileMapper.delete(id);
        updateFiles();
        return count;
    }
}
