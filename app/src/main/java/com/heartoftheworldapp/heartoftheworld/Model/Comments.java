package com.heartoftheworldapp.heartoftheworld.Model;

import java.sql.DatabaseMetaData;
import java.util.Date;

public class Comments {
    private int id;
    private String comment_text;
    private String comment_date;
    private  String comment_username;

    public Comments(int id, String comment_text, String comment_date, String comment_username) {
        this.id = id;
        this.comment_text = comment_text;
        this.comment_date = comment_date;
        this.comment_username = comment_username;
    }

    public Comments() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    public String getComment_username() {
        return comment_username;
    }

    public void setComment_username(String comment_username) {
        this.comment_username = comment_username;
    }
}
