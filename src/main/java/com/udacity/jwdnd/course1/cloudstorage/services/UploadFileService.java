package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.exception.InvalidData;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.file.FileData;
import com.udacity.jwdnd.course1.cloudstorage.model.file.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class UploadFileService {

    @Autowired
    FileMapper fileMapper;

    @Autowired
    UserAuthService userAuthService;

    public int  save(MultipartFile uploadfile) throws IOException, InvalidData {
        int userid = userAuthService.getUserid();
        UploadFile uploadFile = UploadFile.from(uploadfile, userid);
        if(fileMapper.findByName(uploadFile.getFilename()) != null){
            throw new InvalidData("file with same name already exist");
        }

        int id = fileMapper.save(uploadFile);
        return id;
    }



    public List<FileData> getAll(){
        int userid = userAuthService.getUserid();
        return fileMapper.getAll(userid);
    }

    public int delete(int id) {
        int count = fileMapper.delete(id);
        return count;
    }

    public UploadFile findById(int id) {
        return fileMapper.findById(id);
    }
}
