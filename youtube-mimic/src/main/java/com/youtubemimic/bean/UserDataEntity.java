package com.youtubemimic.bean;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class UserDataEntity {
    @Id
    Long userId;
    @Index
    String username;
    @Index
    String emailId;
    @Index
    String password;
    String imgUrl;
    String imgName;

    public UserDataEntity() {
    }

    public UserDataEntity( String username, String emailId, String password, String imgUrl, String imgName) {
       
        this.username = username;
        this.emailId = emailId;
        this.password = password;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgName() {
        return this.imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
}
