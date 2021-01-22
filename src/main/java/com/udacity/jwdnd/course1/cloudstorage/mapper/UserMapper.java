package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.user.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into users (username,salt,password,firstname,lastname) " +
            "values(#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    @Options(keyProperty = "userid", useGeneratedKeys = true)
    int create(User user);

    @Select("select userid, username,salt,password,firstname,lastname from users " +
            "where username = #{username}")
    User findByUserName(String username);

    @Select("select * from users")
    List<User> getAll();

    @Delete("delete from users")
    void clear();
}
