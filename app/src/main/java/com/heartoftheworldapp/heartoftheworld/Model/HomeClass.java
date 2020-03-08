package com.heartoftheworldapp.heartoftheworld.Model;

public class HomeClass {

    int id;
    String city_name;
    int  city_image;
    String cit_name_en;

    public HomeClass(int id, String city_name, int city_image, String cit_name_en) {


        this.id = id;
        this.city_name = city_name;
        this.city_image = city_image;
        this.cit_name_en = cit_name_en;
    }

    public HomeClass() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public int getCity_image() {
        return city_image;
    }

    public void setCity_image(int city_image) {
        this.city_image = city_image;
    }

    public String getCit_name_en() {
        return cit_name_en;
    }

    public void setCit_name_en(String cit_name_en) {
        this.cit_name_en = cit_name_en;
    }
}
