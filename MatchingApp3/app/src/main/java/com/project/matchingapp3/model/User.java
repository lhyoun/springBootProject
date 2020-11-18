package com.project.matchingapp3.model;

import com.project.matchingapp3.task.IP;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class User implements Serializable {
    private int id;
    private String loginid;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String position;
    private String location;
    private String image;
    private String role; // 권한
    //private Timestamp joindate;
    private Team teams;
    private List<Party> partys;

    public List<Party> getPartys() {
        return partys;
    }

    public void setPartys(List<Party> partys) {
        this.partys = partys;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Team getTeams() {
        return teams;
    }

    public void setTeams(Team teams) {
        this.teams = teams;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    public Timestamp getJoindate() {
//        return joindate;
//    }
//
//    public void setJoindate(Timestamp joindate) {
//        this.joindate = joindate;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", loginid='" + loginid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", position='" + position + '\'' +
                ", location='" + location + '\'' +
                ", image='" + image + '\'' +
                ", role='" + role + '\'' +
               // ", joindate=" + joindate +
                ", teams=" + teams +
                '}';
    }

    public String getUrlImage(){
        return IP.serverUrl + "image/" + image;
    }

    public String getUrlTImage(){
        return IP.serverUrl + "image/" + teams.getImage();
    }

    //joindate 출력양식
//    public String getDate() {
//        Timestamp time = this.getJoindate();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        return format.format(time);
//    }
}
