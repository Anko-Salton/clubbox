package com.clubbox.clubbox.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class User implements Serializable {

    public static class List extends ArrayList<User> {
        private static final long serialVersionUID = 7L;
    }

    private Integer id;
    private String email;
    private String password;
    private String name;
    private String forname;
    private String birthdate;
    private String phone;
    private String profilePhoto;
    private Integer level;
    private Club club;



    public Club getClub() {

        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String email, String password, String name, String forname, String birthdate, String phone, String profilePhoto, Integer level) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.forname = forname;
        this.birthdate = birthdate;
        this.phone = phone;
        this.profilePhoto = profilePhoto;
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForname() {
        return forname;
    }

    public void setForname(String forname) {
        this.forname = forname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
