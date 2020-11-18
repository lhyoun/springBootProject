package com.project.matchingapp3.model.dto;

import android.graphics.Bitmap;

import java.io.Serializable;

public class NavDataDto implements Serializable {
    private String username;
    private String nickname;
    private String phone;
    private String image;

    private String t_name;
    private String t_location;
    private String t_image;
    private String t_explaintation;

    public String getT_location() {
        return t_location;
    }

    public void setT_location(String t_location) {
        this.t_location = t_location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getT_image() {
        return t_image;
    }

    public void setT_image(String t_image) {
        this.t_image = t_image;
    }

    public String getT_explaintation() {
        return t_explaintation;
    }

    public void setT_explaintation(String t_explaintation) {
        this.t_explaintation = t_explaintation;
    }

    public String getUrlImage(){
        return "http://172.30.1.58:8000/image/" + image;  //집
        //return "http://10.100.102.15:8000/image/" + image;//학원
    }

    public String getUrlTImage(){
        return "http://172.30.1.58:8000/image/" + t_image;  //집
        //return "http://10.100.102.15:8000/image/" + t_image;//학원
    }
}
