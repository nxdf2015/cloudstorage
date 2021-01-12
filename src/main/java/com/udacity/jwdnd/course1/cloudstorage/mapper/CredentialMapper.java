package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.credential.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Insert("insert into credentials (url,username,password,key,userid) " +
            "values(#{url}, #{username}, #{password}, #{key}, #{userid})")
    @Options(keyProperty = "credentialid" ,useGeneratedKeys = true)
    int create(Credential credential);

    @Select("select userid, credentialid, key, url,username,password from credentials where userid = #{userid}")
    List<Credential> getAll(int userid);

    @Delete("delete from credentials where credentialid=#{credentialid}")
    int delete(int credentialid);

    @Update("update credentials set url=#{url} , username=#{username} , password = #{password}, key= #{key}  where credentialid=#{credentialid}")
    int update(Credential credential);

    @Select("select userid,credentialid, key, url,username,password from credentials where credentialid = #{credentialid}")
    Credential findOne(int credentialid);
}
