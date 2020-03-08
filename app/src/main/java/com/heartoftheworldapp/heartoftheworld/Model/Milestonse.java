package com.heartoftheworldapp.heartoftheworld.Model;

public class Milestonse {
    private  int id;
    private String Milestonse__ar_desc;
    private  String Milestonse__en__desc;
    private  String  Milestonse_image;

    public Milestonse(int id, String milestonse__ar_desc, String milestonse__en__desc, String milestonse_image) {
        this.id = id;
        Milestonse__ar_desc = milestonse__ar_desc;
        Milestonse__en__desc = milestonse__en__desc;
        Milestonse_image = milestonse_image;
    }

    public Milestonse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMilestonse__ar_desc() {
        return Milestonse__ar_desc;
    }

    public void setMilestonse__ar_desc(String milestonse__ar_desc) {
        Milestonse__ar_desc = milestonse__ar_desc;
    }

    public String getMilestonse__en__desc() {
        return Milestonse__en__desc;
    }

    public void setMilestonse__en__desc(String milestonse__en__desc) {
        Milestonse__en__desc = milestonse__en__desc;
    }

    public String getMilestonse_image() {
        return Milestonse_image;
    }

    public void setMilestonse_image(String milestonse_image) {
        Milestonse_image = milestonse_image;
    }
}
