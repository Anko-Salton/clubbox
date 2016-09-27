package com.clubbox.clubbox.model;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by cdsm16 on 12/04/2016.
 */
public abstract class GenericEvent  implements Serializable {

    private Integer id;
    private Date date;
    private String time;
    private String place;
    private String address;
    private Long zipcode;
    private String city;

    public GenericEvent(Integer id) {
        this.id = id;
    }

    public GenericEvent(Integer id, Date date, String time, String place, String address, Long zipcode, String city) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.place = place;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}

