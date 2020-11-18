package com.project.matchingapp3.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Party implements Serializable {
    private int id;
    private int roleNumber; // 권한 / 팀:초대받은거, 개인:초대요청			domain : 1,2 // 1이면 개인이 팀한테 가입 신청 // 2면 팀이 개인한테 가입 요청
    private User user; // 신청 한 유저
    private Team team; // 신청 받은 팀
    private Timestamp requestDate;	// 신청 들어온 시간

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleNumber() {
        return roleNumber;
    }

    public void setRoleNumber(int roleNumber) {
        this.roleNumber = roleNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    @Override
    public String toString() {
        return "Party{" +
                "id=" + id +
                ", roleNumber=" + roleNumber +
                ", user=" + user +
                ", team=" + team +
                ", requestDate=" + requestDate +
                '}';
    }
}
