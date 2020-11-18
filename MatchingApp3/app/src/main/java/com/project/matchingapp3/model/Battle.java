package com.project.matchingapp3.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Battle implements Serializable {
    private int id;
    private TeamInfo teamInfo1;
    private TeamInfo teamInfo2;
    private String location;
    private Timestamp createDate;
    private String matchDate;
    private String info;
    private int role; //0 일때 신청 1일때 수락 2일때 거절
    private Team requestTeam;
    private Team responseTeam;
    private Team winerTeam;

    @Override
    public String toString() {
        return "Battle{" +
                "id=" + id +
                ", teamInfo1=" + teamInfo1 +
                ", teamInfo2=" + teamInfo2 +
                ", location='" + location + '\'' +
                ", createDate=" + createDate +
                ", matchDate='" + matchDate + '\'' +
                ", info='" + info + '\'' +
                ", role=" + role +
                ", requestTeam=" + requestTeam +
                ", responseTeam=" + responseTeam +
                ", winerTeam=" + winerTeam +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TeamInfo getTeamInfo1() {
        return teamInfo1;
    }

    public void setTeamInfo1(TeamInfo teamInfo1) {
        this.teamInfo1 = teamInfo1;
    }

    public TeamInfo getTeamInfo2() {
        return teamInfo2;
    }

    public void setTeamInfo2(TeamInfo teamInfo2) {
        this.teamInfo2 = teamInfo2;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Team getRequestTeam() {
        return requestTeam;
    }

    public void setRequestTeam(Team requestTeam) {
        this.requestTeam = requestTeam;
    }

    public Team getResponseTeam() {
        return responseTeam;
    }

    public void setResponseTeam(Team responseTeam) {
        this.responseTeam = responseTeam;
    }

    public Team getWinerTeam() {
        return winerTeam;
    }

    public void setWinerTeam(Team winerTeam) {
        this.winerTeam = winerTeam;
    }

    //joindate 출력양식
    public String getDateToString() {
        Timestamp time = this.getCreateDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(time);
    }
}
