package com.clubbox.clubbox.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by cdsm16 on 12/04/2016.
 */
public class Convocation implements Serializable{

    private Integer id;
    private Integer response;
    private GenericEvent event;
    private User user;

    public Convocation(Integer id, Integer response, GenericEvent event, User user) {
        this.id = id;
        this.response = response;
        this.event = event;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
        this.response = response;
    }

    public GenericEvent getEvent() {
        return event;
    }

    public void setEvent(GenericEvent event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
