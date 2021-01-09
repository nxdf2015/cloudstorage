package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.credential.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.credential.CredentialForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class CredentialService {

    private List<Credential> credentials;
    private Random random;

    @Autowired
    CredentialMapper credentialMapper;

    @Autowired
    EncryptionService encryptionService;

    public CredentialService() {
        credentials = new ArrayList<>();
        random = new Random();
    }

    public List<Credential> getAll(){
       return credentials.stream().map(this::decode).collect(Collectors.toList());
    }

    public int create(CredentialForm credentialForm, int userid){
        Credential credential = credentialFrom(credentialForm,userid);
        int id = credentialMapper.create(credential);
        updateCredentials();
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


    private void updateCredentials() {
        this.credentials = credentialMapper.getAll();
    }

    public int delete(int credentialid) {
        int count =  credentialMapper.delete(credentialid);
        updateCredentials();
        return count;
    }

    public int  update(CredentialForm credentialForm , int credentialid,int userid) {
        Credential credential = credentialFrom(credentialForm,userid);
        credential.setCredentialid(credentialid);
        int count = credentialMapper.update(credential);
        updateCredentials();
        return count;
    }
}
