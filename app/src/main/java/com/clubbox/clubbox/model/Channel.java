package com.clubbox.clubbox.model;

import java.io.Serializable;
public class Channel implements Serializable {


    private Integer id;
    private String name;
    private Club idClub;

    public Channel() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Club getIdClub() {
        return idClub;
    }

    public void setIdClub(Club idClub) {
        this.idClub = idClub;
    }



}

