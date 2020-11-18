package com.project.matchingapp3.model;

import java.io.Serializable;
import java.util.List;

public class TeamInfo implements Serializable {
    private int id;
    private Team team;
    private User user1;
    private User user2;
    private User user3;
    private User user4;
    private User user5;
    private User user6;
    private User user7;
    private User user8;
    private User user9;
    private User user10;
    private User user11;
    private List<Battle> teamInfo1;
    private List<Battle> teamInfo2;

    @Override
    public String toString() {
        return "TeamInfo{" +
                "id=" + id +
                ", team=" + team +
                ", user1=" + user1 +
                ", user2=" + user2 +
                ", user3=" + user3 +
                ", user4=" + user4 +
                ", user5=" + user5 +
                ", user6=" + user6 +
                ", user7=" + user7 +
                ", user8=" + user8 +
                ", user9=" + user9 +
                ", user10=" + user10 +
                ", user11=" + user11 +
                ", teamInfo1=" + teamInfo1 +
                ", teamInfo2=" + teamInfo2 +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public User getUser3() {
        return user3;
    }

    public void setUser3(User user3) {
        this.user3 = user3;
    }

    public User getUser4() {
        return user4;
    }

    public void setUser4(User user4) {
        this.user4 = user4;
    }

    public User getUser5() {
        return user5;
    }

    public void setUser5(User user5) {
        this.user5 = user5;
    }

    public User getUser6() {
        return user6;
    }

    public void setUser6(User user6) {
        this.user6 = user6;
    }

    public User getUser7() {
        return user7;
    }

    public void setUser7(User user7) {
        this.user7 = user7;
    }

    public User getUser8() {
        return user8;
    }

    public void setUser8(User user8) {
        this.user8 = user8;
    }

    public User getUser9() {
        return user9;
    }

    public void setUser9(User user9) {
        this.user9 = user9;
    }

    public User getUser10() {
        return user10;
    }

    public void setUser10(User user10) {
        this.user10 = user10;
    }

    public User getUser11() {
        return user11;
    }

    public void setUser11(User user11) {
        this.user11 = user11;
    }

    public List<Battle> getTeamInfo1() {
        return teamInfo1;
    }

    public void setTeamInfo1(List<Battle> teamInfo1) {
        this.teamInfo1 = teamInfo1;
    }

    public List<Battle> getTeamInfo2() {
        return teamInfo2;
    }

    public void setTeamInfo2(List<Battle> teamInfo2) {
        this.teamInfo2 = teamInfo2;
    }
}
