package com.project.matchingapp3.model;

import java.io.Serializable;
import java.util.List;

public class Score implements Serializable {
    private int id;
    private int win;
    private int draw;
    private int lose;
    private int total;
    private int rank;
    private Team team;
    private List<Team> score;

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", win=" + win +
                ", draw=" + draw +
                ", lose=" + lose +
                ", total=" + total +
                ", rank=" + rank +
                ", team=" + team +
                ", score=" + score +
                '}';
    }

    public String getSummary(){
        return win+"승 "+draw+"무 "+lose+"패 승점="+total;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Team> getScore() {
        return score;
    }

    public void setScore(List<Team> score) {
        this.score = score;
    }
}
