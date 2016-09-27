package com.clubbox.clubbox.model;

import java.util.Date;

/**
 * Created by cdsm16 on 12/04/2016.
 */
public class Event extends GenericEvent {

    private Club club;
    private String name;
    private String description;

    public Event(Integer id, Date date, String time, String place, String address, Long zipcode, String city, Club club, String name, String description) {
        super(id, date, time, place, address, zipcode, city);
        this.club = club;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
