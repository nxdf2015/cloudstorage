package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.user.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    HashService hashService;

    public User findById(int userid){

        return null;
    }

    public List<User> getAll(){
        return userMapper.getAll();
    }
    private String generateKey(){
        Random random = new Random();
        byte[] key = new byte[16];
        random.nextBytes(key);
        return Base64.encodeBase64(key).toString();
    }

    private User hashPassword(User user){
        user.setSalt(generateKey());
        user.setPassword(hashService.getHashedValue(user.getPassword(),user.getSalt()));
        return user;
    }

    public boolean save(User user){
        if(exist(user)){
            throw new IllegalArgumentException("user already exist");
        }
        return  userMapper.create(hashPassword(user)) == 1;
    }

    private boolean exist(User user) {
        return userMapper.findByUserName(user.getUsername()) != null;
    }

    public User findByUserName(String name) {
        return userMapper.findByUserName(name);
    }

    // TODO add reset users
}
