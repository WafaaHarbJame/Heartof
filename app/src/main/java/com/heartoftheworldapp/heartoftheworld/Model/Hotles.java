package com.heartoftheworldapp.heartoftheworld.Model;

public class Hotles {
    private int id;
    private String Hotle_ar_name;
    private String Hotle_en_name;
    private String Hotle_ar_desc;
    private String Hotle_en__desc;
    private String Hotle_link;
    private String Hotle_price;
    private String Hotle_image;
    private boolean is_favorite;
    private String City_Name;

    public Hotles(int id, String hotle_ar_name, String hotle_en_name, String hotle_ar_desc, String hotle_en__desc, String hotle_link, String hotle_price, String hotle_image, boolean is_favorite, String city_Name) {
        this.id = id;
        Hotle_ar_name = hotle_ar_name;
        Hotle_en_name = hotle_en_name;
        Hotle_ar_desc = hotle_ar_desc;
        Hotle_en__desc = hotle_en__desc;
        Hotle_link = hotle_link;
        Hotle_price = hotle_price;
        Hotle_image = hotle_image;
        this.is_favorite = is_favorite;
        City_Name = city_Name;
    }

    public Hotles() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotle_ar_name() {
        return Hotle_ar_name;
    }

    public void setHotle_ar_name(String hotle_ar_name) {
        Hotle_ar_name = hotle_ar_name;
    }

    public String getHotle_en_name() {
        return Hotle_en_name;
    }

    public void setHotle_en_name(String hotle_en_name) {
        Hotle_en_name = hotle_en_name;
    }

    public String getHotle_ar_desc() {
        return Hotle_ar_desc;
    }

    public void setHotle_ar_desc(String hotle_ar_desc) {
        Hotle_ar_desc = hotle_ar_desc;
    }

    public String getHotle_en__desc() {
        return Hotle_en__desc;
    }

    public void setHotle_en__desc(String hotle_en__desc) {
        Hotle_en__desc = hotle_en__desc;
    }

    public String getHotle_link() {
        return Hotle_link;
    }

    public void setHotle_link(String hotle_link) {
        Hotle_link = hotle_link;
    }

    public String getHotle_price() {
        return Hotle_price;
    }

    public void setHotle_price(String hotle_price) {
        Hotle_price = hotle_price;
    }

    public String getHotle_image() {
        return Hotle_image;
    }

    public void setHotle_image(String hotle_image) {
        Hotle_image = hotle_image;
    }

    public boolean isIs_favorite() {
        return is_favorite;
    }

    public void setIs_favorite(boolean is_favorite) {
        this.is_favorite = is_favorite;
    }

    public String getCity_Name() {
        return City_Name;
    }

    public void setCity_Name(String city_Name) {
        City_Name = city_Name;
    }
}
