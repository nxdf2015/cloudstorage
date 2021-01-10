package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.file.FileData;
import com.udacity.jwdnd.course1.cloudstorage.model.file.UploadFile;
import org.apache.ibatis.annotations.*;
import org.apache.tomcat.util.http.fileupload.FileUpload;

import java.io.InputStream;
import java.util.List;

@Mapper
public interface FileMapper {
    @Insert("insert into files (filename, contenttype,filesize,userid,filedata)" +
            "values(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(keyProperty = "fileid", useGeneratedKeys = true)
    public int  save(UploadFile fileUpload);

    @Select("select fileid, filename, contenttype, filesize, userid from files")
    public List<FileData> getAll();

    @Delete("delete from files where fileid=#{id}")
    int delete(int id);

    @Select("select filename from files where filename = #{filename}")
    String findByName(String filename);
}
