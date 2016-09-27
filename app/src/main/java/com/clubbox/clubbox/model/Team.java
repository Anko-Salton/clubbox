package com.clubbox.clubbox.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by cdsm16 on 12/04/2016.
 */
public class Team implements Serializable {

    public static class List extends ArrayList<Match> {
        private static final long serialVersionUID = 2L;
    }

    private Integer id;
    private Club club;
    private String name;
    private Division division;

    public Team(Integer id) {
        this.id = id;
    }

    public Team(Integer id, Club club, String name, Division division) {
        this.id = id;
        this.club = club;
        this.name = name;
        this.division = division;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }
}
