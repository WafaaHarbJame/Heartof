package com.heartoftheworldapp.heartoftheworld.Model;

public class Catogory {
    int id;
    String catogory_name;
    String catogory_image;
    String city;

    public Catogory(int id, String catogory_name, String catogory_image, String city) {
        this.id = id;
        this.catogory_name = catogory_name;
        this.catogory_image = catogory_image;
        this.city = city;
    }

    public Catogory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatogory_name() {
        return catogory_name;
    }

    public void setCatogory_name(String catogory_name) {
        this.catogory_name = catogory_name;
    }

    public String getCatogory_image() {
        return catogory_image;
    }

    public void setCatogory_image(String catogory_image) {
        this.catogory_image = catogory_image;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
