package com.clubbox.clubbox.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Match  implements Serializable {
    private Integer id;
    private String datetime;
    private String place;
    private String address;
    private Long zipcode;
    private String city;
    private Team teamHome;
    private Team teamAway;
    private Integer scoreHome;
    private Integer scoreAway;
    private String resumeHome;
    private String resumeAway;

    public static class List extends ArrayList<Match> {
        private static final long serialVersionUID = 5L;
    }

    public Match(Integer id, String date, String time, String place, String address, Long zipcode, String city, Team teamHome, Team teamAway, Integer scoreHome, Integer scoreAway, String resumeHome, String resumeAway) {
        this.id = id;
        this.datetime = date;
        this.place = place;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.scoreHome = scoreHome;
        this.scoreAway = scoreAway;
        this.resumeHome = resumeHome;
        this.resumeAway = resumeAway;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getZipcode() {
        return zipcode;
    }

    public void setZipcode(Long zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String toString() {
        return this.getTeamHome() + " " + this.getScoreHome().toString() + " - " + this.getScoreAway().toString() + " " + this.getTeamAway();
    }


    public Integer getScoreHome() {
        return scoreHome;
    }

    public void setScoreHome(Integer scoreHome) {
        this.scoreHome = scoreHome;
    }

    public Integer getScoreAway() {
        return scoreAway;
    }

    public void setScoreAway(Integer scoreAway) {
        this.scoreAway = scoreAway;
    }

    public String getResumeHome() {
        return resumeHome;
    }

    public void setResumeHome(String resumeHome) {
        this.resumeHome = resumeHome;
    }

    public String getResumeAway() {
        return resumeAway;
    }

    public void setResumeAway(String resumeAway) {
        this.resumeAway = resumeAway;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Team getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(Team teamHome) {
        this.teamHome = teamHome;
    }

    public Team getTeamAway() {
        return teamAway;
    }

    public void setTeamAway(Team teamAway) {
        this.teamAway = teamAway;
    }
}
