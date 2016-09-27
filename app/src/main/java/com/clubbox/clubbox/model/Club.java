package com.clubbox.clubbox.model;

import java.io.Serializable;

public class Club implements Serializable {

    private Long id;
    private String name;
    private String address;
    private Long zipcode;
    private String city;
    private String logoPath;
    private boolean isValidate;

    public Club(Long id) {
        this.id = id;
    }

    public Club(Long id, String name, String address, Long zipcode, String city, String logoPath, boolean isValidate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.logoPath = logoPath;
        this.isValidate = isValidate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public boolean isValidate() {
        return isValidate;
    }

    public void setIsValidate(boolean isValidate) {
        this.isValidate = isValidate;
    }
}
