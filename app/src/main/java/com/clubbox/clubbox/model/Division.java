package com.clubbox.clubbox.model;

import java.io.Serializable;

/**
 * Created by cdsm16 on 12/04/2016.
 */
public class Division implements Serializable {

    private Integer id;
    private String name;
    private Departement dept;

    public Division(Integer id, String name, Departement dept) {
        this.id = id;
        this.name = name;
        this.dept = dept;
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

    public Departement getDept() {
        return dept;
    }

    public void setDept(Departement dept) {
        this.dept = dept;
    }
}
