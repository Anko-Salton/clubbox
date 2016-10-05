package com.clubbox.clubbox.model;

/**
 * Created by floriantainlot on 05/10/2016.
 */

public class Availability {

    private int id;
    private User userId;
    private Match idMatch;
    private boolean availability;

    public Availability(User userId, Match idMatch, boolean availability) {
        this.userId = userId;
        this.idMatch = idMatch;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Match getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(Match idMatch) {
        this.idMatch = idMatch;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
