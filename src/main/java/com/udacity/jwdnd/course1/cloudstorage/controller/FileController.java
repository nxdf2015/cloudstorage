package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.exception.InvalidData;
import com.udacity.jwdnd.course1.cloudstorage.services.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    UploadFileService uploadFileService;

    @PostMapping
    public String uploadFile(MultipartFile uploadfile, Model model) throws IOException, InvalidData {


            uploadFileService.save(uploadfile,1);

        model.addAttribute("success",true);
        return "result";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model){
        uploadFileService.delete(id);
        model.addAttribute("success",true);
        return "result";
    }
}
