package com.clubbox.clubbox.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Channel implements Serializable {

    public static class List extends ArrayList<Channel> {
        private static final long serialVersionUID = 6L;
    }
    private Integer id;
    private String name;
    private ArrayList<User> users;
    private ArrayList<Message> messages;

    public Channel(Integer id, String name, ArrayList<User> users, ArrayList<Message> messages) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.messages = messages;
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

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public String toString() {
        return this.id + " " + this.name;
    }

    public void sendMessage(Message msg) {
        messages.add(msg);
    }

    public Message getLastMessage() {
        int z = messages.size() - 1;
        return messages.get(z);
    }
}

