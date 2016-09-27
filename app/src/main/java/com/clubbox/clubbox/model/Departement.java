package com.clubbox.clubbox.model;

import java.io.Serializable;

/**
 * Created by cdsm16 on 12/04/2016.
 */
public class Departement implements Serializable{

    private Integer code;
    private String name;

    public Departement(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
