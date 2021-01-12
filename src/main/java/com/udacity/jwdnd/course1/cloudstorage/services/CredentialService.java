package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.credential.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.credential.CredentialForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class CredentialService {


    private Random random;


    @Autowired
    UserAuthService userAuthService;

    @Autowired
    CredentialMapper credentialMapper;

    @Autowired
    EncryptionService encryptionService;

    @Autowired
    public CredentialService() {
        random = new Random();
    }

    public List<Credential> getAll(){
        int userid = userAuthService.getUserid();
        return credentialMapper.getAll(userid);
    }

    public int create(CredentialForm credentialForm){
        int userid = userAuthService.getUserid();
        Credential credential = credentialFrom(credentialForm,userid);
        int id = credentialMapper.create(credential);
        return id;
    }

    private Credential decode(Credential credential){
        Credential clone =  credential.clone();
        clone.setPassword(encryptionService.decryptValue(credential.getPassword(),credential.getKey()));
        return clone;
    }

    private Credential credentialFrom(CredentialForm credentialForm,int userid){
        Credential credential = new Credential();
        credential.setUserid(userid);
        credential.setUrl(credentialForm.getUrl());
        credential.setUsername(credentialForm.getUsername());
        String key = generateKey();
        credential.setKey(key);
        credential.setPassword(encryptionService.encryptValue(credentialForm.getPassword(),key));
        return credential;
    }

    private String generateKey(){
        byte[] key = new byte[16];
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }


    public int delete(int credentialid) {
        int count =  credentialMapper.delete(credentialid);
        return count;
    }

    public int  update(CredentialForm credentialForm, int credentialid) {
        int userid = userAuthService.getUserid();
        Credential credential = credentialFrom(credentialForm,userid);
        credential.setCredentialid(credentialid);
        int count = credentialMapper.update(credential);
        return count;
    }

    public Credential decodePassword(int id) {
        Credential credential = credentialMapper.findOne(id);
        return decode(credential);
    }
}
