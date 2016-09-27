package com.clubbox.clubbox.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Message implements Serializable {

    public static class List extends ArrayList<Message> {
        private static final long serialVersionUID = 8L;
    }

    private Integer id;
    private Date dateAdd;
    private String content;
    private User user;
    private boolean left;

    public Message() {

    }

    public Message(Integer id) {
        this.id = id;
    }

    public Message(Integer id, Date dateAdd, String content, User user) {
        this.id = id;
        this.dateAdd = dateAdd;
        this.content = content;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean getLeft() {
        return this.left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
}
