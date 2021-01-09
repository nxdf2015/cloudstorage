package com.udacity.jwdnd.course1.cloudstorage.model.credential;

import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class Credential {
    private int credentialid,userid;
    private String url,username,password;
    private String key;
    private static Random random;

    public Credential() {
    }

    public Credential(int credentialid, String url, String username, String password, String key, int userid) {
        this.credentialid = credentialid;
        this.userid = userid;
        this.url = url;
        this.username = username;
        this.password = password;
        this.key = key;
    }

    public int getCredentialid() {
        return credentialid;
    }

    public void setCredentialid(int credentialid) {
        this.credentialid = credentialid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public Credential clone()  {
        return new Credential(credentialid, url, username, password, key, userid);
    }

    @Override
    public String toString() {
        return "Credential{" +
                "credentialid=" + credentialid +
                ", userid=" + userid +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}

