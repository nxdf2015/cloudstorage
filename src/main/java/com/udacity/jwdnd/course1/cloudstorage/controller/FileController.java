package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.exception.InvalidData;
import com.udacity.jwdnd.course1.cloudstorage.model.file.UploadFile;
import com.udacity.jwdnd.course1.cloudstorage.services.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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

    @GetMapping("/download/{id}")
    @ResponseBody
    public ResponseEntity<Resource>  download(@PathVariable("id") int id) throws IOException {
        UploadFile uploadFile =  uploadFileService.findById(id);
        Resource resource = new ByteArrayResource(uploadFile.getFiledata().readAllBytes());
      return   ResponseEntity.ok()
              .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + uploadFile.getFilename() + "\"")
                .contentLength(Integer.parseInt(uploadFile.getFilesize()))
                .contentType(MediaType.valueOf(uploadFile.getContenttype()))
                .body(resource);

    }
}
