package com.clubbox.clubbox.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class News implements Serializable {
    private static final long serialVersionUID = 0L;

    private Integer id;
    private Club club;
    private String dateadd;
    private String title;
    private String content;

    public static class List extends ArrayList<News>{
        private static final long serialVersionUID = 1L;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public String getDateFormatFR() {
        return (this.dateadd);
    }

    public String getDateadd() {
        return dateadd;
    }

    public void setDateadd(String dateadd) {
        this.dateadd = dateadd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NewsActivity{" +
                "id=" + id +
                ", club=" + club +
                ", dateadd=" + dateadd +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
