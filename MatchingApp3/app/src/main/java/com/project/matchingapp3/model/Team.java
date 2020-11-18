package com.project.matchingapp3.model;

import com.project.matchingapp3.task.IP;

import java.io.Serializable;
import java.util.List;

public class Team implements Serializable {
    private int id;
    private String name;
    private String location;
    private String explaintation; // 팀설명
    private String image;
    private User owner;
    private List<User> users;
    private List<Party> partys;
    private List<Battle> battle1;
    private List<Battle> battle2;
    private Score score;

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", explaintation='" + explaintation + '\'' +
                ", image='" + image + '\'' +
                ", owner=" + owner +
                ", users=" + users +
                ", partys=" + partys +
                ", battle1=" + battle1 +
                ", battle2=" + battle2 +
                ", score=" + score +
                '}';
    }

    public List<Party> getPartys() {
        return partys;
    }

    public void setPartys(List<Party> partys) {
        this.partys = partys;
    }

    public List<Battle> getBattle1() {
        return battle1;
    }

    public void setBattle1(List<Battle> battle1) {
        this.battle1 = battle1;
    }

    public List<Battle> getBattle2() {
        return battle2;
    }

    public void setBattle2(List<Battle> battle2) {
        this.battle2 = battle2;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Team(){}

    public Team(String name, String location, String image) {
        this.name = name;
        this.location = location;
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExplaintation() {
        return explaintation;
    }

    public void setExplaintation(String explaintation) {
        this.explaintation = explaintation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getUrlImage(){
        return IP.serverUrl + "image/" + image;
    }
}
